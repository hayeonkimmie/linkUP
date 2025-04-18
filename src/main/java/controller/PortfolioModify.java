package controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.Portfolio;
import service.IPortfolioService;
import service.PortfolioService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
        if(userId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        }
        IPortfolioService service = new PortfolioService();
        /* Map<Integer, String> projectInfoMap = service.projectInfoForProtfolio(userId);*/
        request.getRequestDispatcher("/freelancer/my_page_portfolio_modify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String userId = (String) request.getAttribute("userId");
        userId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        System.out.println("파일 저장경로: = " + path);
        int size = 10*1024*1024;//10mb
        try {
            MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());//파일이 저장되는 시점
            String freelancerId = multi.getParameter("userId");
            freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
            Portfolio portfolio = new Portfolio();
            portfolio.setFreelancerId(freelancerId);
            portfolio.setTitle(multi.getParameter("title"));
            portfolio.setIntroduce(multi.getParameter("introduction"));
            portfolio.setTeamRole(multi.getParameter("teamRole"));
            portfolio.setSkillDescription(multi.getParameter("skillDescription"));
            String monthStr = request.getParameter("portProjStart"); // 예: "2025-04"
            String monthEnd = request.getParameter("portProjEnd");
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

            if (monthEnd != null && !monthStr.trim().isEmpty()) {
                try {
                    // "yyyy-MM" → "yyyy-MM-01" 형식으로 보정
                    String fullDateStr = monthEnd + "-01";
                    endDate = Date.valueOf(fullDateStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("날짜 형식 오류: " + monthStr);
                    e.printStackTrace();
                }
            }
            portfolio.setPortProjEnd(endDate);

            portfolio.setSkillDescription(multi.getParameter("skillDescription"));
            if(multi.getParameter("skillDescription") != null && multi.getParameter("skillDescription").contains("^")) {
                portfolio.setSkillDescription(multi.getParameter("skillDescription") );
            } else {
                portfolio.setSkillDescription(null);
            }

            portfolio.setThumbnail(multi.getFilesystemName("thumbnail"));
            if(portfolio.getThumbnail() == null) {
                portfolio.setThumbnail("default.png");
            }
            portfolio.setTeamRole(multi.getParameter("teamRole"));

            // 1) 외부 URL 동적 수집
            Enumeration<String> paramNames = multi.getParameterNames();
            List<String> urls = new ArrayList<>();
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                if (name.startsWith("file-url-")) {
                    String url = multi.getParameter(name);
                    if (url != null && !url.trim().isEmpty()) {
                        urls.add(url + "^");
                    }
                }
                int idx = 0;
            }
            portfolio.setExternalUrl(urls.toString());

            // 2) 첨부파일 동적 수집
            List<String> attachments = new ArrayList<>();
            Enumeration<?> fileFields = multi.getFileNames();
            String attachment = null;
            while (fileFields.hasMoreElements()) {
                attachment = "";
                String field = (String) fileFields.nextElement();
                if (field.startsWith("attachment-")) {
                    String saved = multi.getFilesystemName(field);
                    if (saved != null) {
                        attachments.add(saved);
                    }
                }
            }
            portfolio.setAttachment(attachments.toString());

            if(multi.getParameter("portfolioId") == null) {
                portfolio.setPortfolioId(null);
            } else {
                portfolio.setPortfolioId(Integer.parseInt(multi.getParameter("portfolioId")));
            }
            if(multi.getParameter("tempSaved") != null && multi.getParameter("tempSaved").equals("true")) {
                portfolio.setTempSaved(true);

            } else {
                portfolio.setTempSaved(false);
            }
            System.out.println(portfolio);
            IPortfolioService service = new PortfolioService();
            service.modifyPortfolio(portfolio);
            request.getRequestDispatcher("/freelancer/my_page_portfolio_detail.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}