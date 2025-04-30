package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.Portfolio;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@WebServlet("/my-page/portfolio-modify")
public class PortfolioModify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioModify() {
        super();
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect("/linkup/login");
            return;
        };
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        System.out.println("portfolioId: " + portfolioId);
        IPortfolioService service = new PortfolioService();
        try {
            if (portfolioId == null) {
                request.setAttribute("err", "포트폴리오 ID가 없습니다.");
                response.sendRedirect(request.getContextPath() + "/my-page/portfolio-list");
//                request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
            } else if(!service.isPortfolioOwner(freelancerId, portfolioId)) {
                request.setAttribute("err", "본인이 작성하지 않은 포트폴리오를 수정할 수 없습니다.");
                response.sendRedirect(request.getContextPath() + "/my-page/portfolio-list");
            } else {
                Map<Integer, String> projectInfoMap = service.projectInfoForPortfolio(freelancerId);
                Portfolio portfolio = service.selectPortfolioById(portfolioId);
                request.setAttribute("projectInfoMap", projectInfoMap);
                portfolio.setProjectInfoMap(projectInfoMap);
                request.setAttribute("projectInfoMap", portfolio.getProjectInfoMap());
                request.setAttribute("portfolio", portfolio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Map<Integer, String> projectInfoMap = service.projectInfoForProtfolio(userId);*/
        request.getRequestDispatcher("/freelancer/portfolio_modify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        System.out.println("파일 저장경로: = " + path);
        int size = 10 * 1024 * 1024;//10mb
        String freelancerId = (String) request.getSession().getAttribute("userId");
        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());//파일이 저장되는 시점
        IPortfolioService service = new PortfolioService();
        try {
            Portfolio portfolio = service.selectPortfolioById(portfolioId);
            JSONParser parser = new JSONParser();
            portfolio.setFreelancerId(freelancerId);
            portfolio.setTitle(multi.getParameter("title"));
            portfolio.setIntroduce(multi.getParameter("introduction"));
            portfolio.setTeamRole(multi.getParameter("teamRole"));
            portfolio.setSkillDescription(multi.getParameter("skillDescriptionHidden"));
            String monthStr = multi.getParameter("portProjStart");
            String monthEnd = multi.getParameter("portProjEnd");
            if(multi.getParameter("projectId") != null) {
                System.out.println("project-id : "+multi.getParameter("projectId"));
                portfolio.setProjectId(Integer.parseInt(multi.getParameter("projectId")));
            } else {
                portfolio.setProjectId(null);
            }
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

        // . ExternalUrlList
            List<String> externalUrlList = new ArrayList<>();
            String externalUrlListJson = multi.getParameter("externalUrlListJson");
            if (externalUrlListJson != null && !externalUrlListJson.isEmpty()) {
                JSONArray urlArray = (JSONArray) parser.parse(externalUrlListJson);
                for (Object url : urlArray) {
                    externalUrlList.add((String) url);
                }
                System.out.println("1차 체크포인트 externalUrlList :" + externalUrlList);
                portfolio.setExternalUrl(String.join("^", externalUrlList));
            }

            // 5. AttachmentList
            List<String> attachmentList = new ArrayList<>();
            String attachmentListJson = multi.getParameter("attachmentListJson");
            if (attachmentListJson != null && !attachmentListJson.isEmpty()) {
                JSONArray attachmentArray = (JSONArray) parser.parse(attachmentListJson);
                for (Object fileName : attachmentArray) {
                    attachmentList.add((String) fileName);
                }
                System.out.println("2차 체크포인트 attachmentList :" + attachmentList);
                portfolio.setAttachment(String.join("^", attachmentList));
            }
            String skillDescription = multi.getParameter("skillDescription"); // "Java^Spring^MySQL"
            System.out.println("3차 체크포인트 skillDescription : " + skillDescription);
            portfolio.setSkillDescription(skillDescription);

            String newThumbnail = multi.getFilesystemName("thumbnail");
            if (newThumbnail == null || newThumbnail.isEmpty()) {
                Portfolio original = service.selectPortfolioById(portfolioId);
                portfolio.setThumbnail(original.getThumbnail());
            } else {
                portfolio.setThumbnail(newThumbnail);
            }

            System.out.println(portfolio);
            service.modifyPortfolio(portfolio);
            service.selectPortfolioById(portfolio.getPortfolioId());
            response.sendRedirect(request.getContextPath() + "/my-page/portfolio-detail?id=" + portfolio.getPortfolioId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}