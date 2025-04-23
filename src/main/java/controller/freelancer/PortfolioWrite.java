package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BoardWrite
 */
@WebServlet("/my-page/portfolio-write")
public class PortfolioWrite extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioWrite() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        if(freelancerId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        }
        IPortfolioService service = new PortfolioService();
        try {
            Map<Integer, String> projectInfoMap = service.projectInfoForPortfolio(freelancerId);
            System.out.println(projectInfoMap.toString());
            if (projectInfoMap != null) {
                request.setAttribute("projectInfoMap", projectInfoMap);
            } else {
                request.setAttribute("projectInfoMap", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/freelancer/my_page_portfolio_write.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        System.out.println("파일 저장경로: = " + path);
        int size = 10*1024*1024;//10mb
        try {
            MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());//파일이 저장되는 시점
            Portfolio portfolio = new Portfolio();
            portfolio.setFreelancerId(freelancerId);
            portfolio.setTitle(multi.getParameter("title"));
            portfolio.setIntroduce(multi.getParameter("introduction"));
            portfolio.setTeamRole(multi.getParameter("teamRole"));
            portfolio.setSkillDescription(multi.getParameter("skillDescriptionHidden"));
            String monthStr = multi.getParameter("portProjStart");
            String monthEnd = multi.getParameter("portProjEnd");
            Date startDate = null;
            Date endDate = null;
            if (monthStr != null && !monthStr.trim().isEmpty()) {
                try {
                    String fullDateStr = monthStr + "-01";
                    startDate = Date.valueOf(fullDateStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("날짜 형식 오류: " + monthStr);
                    e.printStackTrace();
                }
            }
            portfolio.setPortProjStart(startDate);

            if (monthEnd != null && !monthEnd.trim().isEmpty() &&monthEnd.matches("\\d{4}-\\d{2}")) {
                try {
                    String fullDateStr = monthEnd + "-01";
                    endDate = Date.valueOf(fullDateStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("날짜 형식 오류: " + monthStr);
                    endDate = null;
                    e.printStackTrace();
                }
            }
            portfolio.setPortProjEnd(endDate);

            String skillDesc = multi.getParameter("skillDescriptionHidden");
            portfolio.setSkillDescription((skillDesc));

            portfolio.setThumbnail(multi.getFilesystemName("thumbnail"));
            if(portfolio.getThumbnail() == null) {
                portfolio.setThumbnail("default.png");
            }
            portfolio.setTeamRole(multi.getParameter("teamRole"));

            // 1) 외부 URL 동적 수집
            Enumeration<String> paramNames = multi.getParameterNames();
            List<String> urls = new ArrayList<>();
            if(urls != null) {
                while (paramNames.hasMoreElements()) {
                    String name = paramNames.nextElement();
                    if (name.startsWith("file-url-")) {
                        String url = multi.getParameter(name);
                        if (url != null && !url.trim().isEmpty()) {
                            urls.add(url);
                        }
                    }
                }
            }else{
                portfolio.setExternalUrl(null);
            }
            portfolio.setExternalUrl(String.join("^", urls));

            // 2) 첨부파일 동적 수집
            List<String> attachment = new ArrayList<>();
            Enumeration<?> fileFields = multi.getFileNames();
            if(fileFields != null) {
                while (fileFields.hasMoreElements()) {
                    String field = (String) fileFields.nextElement();
                    if (field.startsWith("attachment-")) {
                        String saved = multi.getFilesystemName(field);
                        if (saved != null) {
                            attachment.add(saved);
                        }
                    } else {
                        continue;
                    }
                    portfolio.setAttachment(String.join("^", attachment));
                }
            } else {
                portfolio.setAttachment(null);
            }

            if(multi.getParameter("tempSaved") != null && multi.getParameter("tempSaved").equals("true")) {
                portfolio.setTempSaved(true);
            } else {
                portfolio.setTempSaved(false);
            }
            if(multi.getParameter("portfolioId") != null) {
                portfolio.setPortfolioId(Integer.parseInt(multi.getParameter("portfolioId")));
            } else {
                portfolio.setPortfolioId(null);
            }
            System.out.println(portfolio);
            IPortfolioService service = new PortfolioService();

            Integer newPortfolioId = service.writePortfolio(portfolio);
            /*response.sendRedirect(request.getContextPath() + "/portfolio-portfolio-detail?id=" + newPortfolioId);*/
            request.setAttribute("portfolio", portfolio);
            request.getRequestDispatcher("/freelancer/my_page_portfolio_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오 등록 중 오류 발생했습니다.");
            request.getRequestDispatcher("/freelancer/my_page_portfolio_write.jsp").forward(request, response);
        }
    }
}
