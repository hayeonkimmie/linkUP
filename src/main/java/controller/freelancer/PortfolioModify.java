package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebServlet("/my-page/portfolio-modify")
public class PortfolioModify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioModify() {
        super();
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userId = (String) request.getAttribute("userId");
        userId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        if (userId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        }
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        System.out.println("portfolioId: " + portfolioId);
        if (portfolioId == null) {
            request.setAttribute("err", "포트폴리오 ID가 없습니다.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        } else {
            IPortfolioService service = new PortfolioService();
            try {
                Map<Integer, String> projectInfoMap = service.projectInfoForPortfolio(userId);
                Portfolio portfolio = service.selectPortfolioById(portfolioId);
                request.setAttribute("projectInfoMap", projectInfoMap);
                request.setAttribute("portfolio", portfolio);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /* Map<Integer, String> projectInfoMap = service.projectInfoForProtfolio(userId);*/
        request.getRequestDispatcher("/freelancer/portfolio_modify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(portfolioId);
        IPortfolioService service = new PortfolioService();
        System.out.println("portfolioId: " + portfolioId);
        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        System.out.println("파일 저장경로: = " + path);
        int size = 10 * 1024 * 1024;//10mb
        try {
            MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());//파일이 저장되는 시점
            String freelancerId = multi.getParameter("userId");
            freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
            portfolio.setFreelancerId(freelancerId);
            portfolio.setTitle(multi.getParameter("title"));
            portfolio.setIntroduce(multi.getParameter("introduction"));
            portfolio.setTeamRole(multi.getParameter("teamRole"));
            portfolio.setSkillDescription(multi.getParameter("skillDescription"));
            String monthStr = multi.getParameter("portProjStart");
            String monthEnd = multi.getParameter("portProjEnd");
            Date startDate = null;
            Date endDate = null;
            if (monthEnd != null && !monthEnd.trim().isEmpty() && monthEnd.matches("\\d{4}-\\d{2}")) {
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
            portfolio.setSkillDescription((skillDesc != null && skillDesc.contains("^")) ? skillDesc : null);
            portfolio.setThumbnail(multi.getFilesystemName("thumbnail"));
            if (portfolio.getThumbnail() == null) {
                portfolio.setThumbnail("default.png");
            }
            portfolio.setTeamRole(multi.getParameter("teamRole"));

            // 1) 외부 URL 동적 수집
            Enumeration<String> paramNames = multi.getParameterNames();
            List<String> urls = new ArrayList<>();
            if (urls != null) {
                while (paramNames.hasMoreElements()) {
                    String name = paramNames.nextElement();
                    if (name.startsWith("file-url-")) {
                        String url = multi.getParameter(name);
                        if (url != null && !url.trim().isEmpty()) {
                            urls.add(url);
                        }
                    }
                }
            } else {
                portfolio.setExternalUrl(null);
            }
            portfolio.setExternalUrl(String.join("^", urls));

            // 2) 첨부파일 동적 수집
            List<String> attachment = new ArrayList<>();
            Enumeration<?> fileFields = multi.getFileNames();
            if (fileFields != null) {
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
            portfolio.setAttachment(attachment.toString());

            if (multi.getParameter("projectId") == null) {
                portfolio.setProjectId(null);
            } else {
                portfolio.setProjectId(Integer.parseInt(multi.getParameter("projectId")));
            }
            if (multi.getParameter("tempSaved") != null && multi.getParameter("tempSaved").equals("true")) {
                portfolio.setTempSaved(true);
            } else {
                portfolio.setTempSaved(false);
            }
            System.out.println(portfolio);
            service.modifyPortfolio(portfolio);
            service.selectPortfolioById(portfolio.getPortfolioId());
            response.sendRedirect("/my-page/portfolio-detail?id=" + portfolio.getPortfolioId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}