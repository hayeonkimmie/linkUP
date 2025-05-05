package controller.client;

import dto.ProjectMgt;
import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/clientRecruitMgt")
public class ClientRecruitMgtList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final int PAGE_SIZE = 5;
    private final int PAGE_BLOCK = 5;
    public ClientRecruitMgtList() {
        super();
    }

    // 서비스 호출 준비
    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        try {
            HttpSession session = request.getSession();
            Boolean projectUpdated = (Boolean) session.getAttribute("projectUpdated");
            if (projectUpdated != null && projectUpdated) {
                session.removeAttribute("projectUpdated");
            }

            // 오늘 날짜 기준 상태 업데이트
            Map<String, Object> statusParam = new HashMap<>();
            statusParam.put("today", new java.sql.Date(System.currentTimeMillis()));
            service.updateProgressToOngoing(statusParam);
            service.updateProgressToEnd(statusParam);

            // 로그인 사용자 ID
            String clientId = (String) session.getAttribute("userId");
            if (clientId == null || clientId.isEmpty()) {
                clientId = "client001"; // 테스트용
            }

            // 필터 상태 파라미터 처리
            String statusParamRaw = request.getParameter("status");
            String status = (statusParamRaw != null) ? statusParamRaw : "all";
            String dbStatus;

            switch (status) {
                case "open":
                    dbStatus = "구인중";
                    break;
                case "done-start":
                case "done-progress":
                case "done-end":
                    dbStatus = "구인완료";
                    break;
                case "all":
                default:
                    dbStatus = null;
                    break;
            }

            // 현재 페이지 파라미터 처리
            int curPage = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    curPage = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    curPage = 1;
                }
            }

            // 전체 프로젝트 수 조회
            Map<String, Object> countParam = new HashMap<>();
            countParam.put("clientId", clientId);
            countParam.put("status", dbStatus);
            int totalCount = service.getProjectCountByStatus(countParam);

            // 페이징 계산
            int PAGE_SIZE = 5;
            int PAGE_BLOCK = 5;
            int allPage = (int) Math.ceil((double) totalCount / PAGE_SIZE);
            int startRow = (curPage - 1) * PAGE_SIZE;
            int startPage = ((curPage - 1) / PAGE_BLOCK) * PAGE_BLOCK + 1;
            int endPage = Math.min(startPage + PAGE_BLOCK - 1, allPage);

            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(curPage);
            pageInfo.setAllPage(allPage);
            pageInfo.setStartPage(startPage);
            pageInfo.setEndPage(endPage);

            // 프로젝트 리스트 조회
            Map<String, Object> param = new HashMap<>();
            param.put("clientId", clientId);
            param.put("status", dbStatus);
            param.put("startRow", startRow);
            param.put("pageSize", PAGE_SIZE);

            List<ProjectMgt> projectList = service.getProjectByStatus(param);
            for (ProjectMgt project : projectList) {
                if (!"구인완료".equals(project.getStatus())) {
                    project.setProjectProgress("");
                }
            }

            // 전달
            request.setAttribute("projectList", projectList);
            request.setAttribute("status", status); // 원래 선택한 필터 상태 전달
            request.setAttribute("pageInfo", pageInfo);

            // 이동
            request.getRequestDispatcher("./client/recruitmentList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 조회 중 오류 발생");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용 안함
    }

    // 날짜 처리 함수
    private LocalDate parseFlexibleDate(String dateStr) {
        String[] patterns = {
                "yyyy-MM-dd", "yyyy.MM.dd", "yyyy/MM/dd",
                "yyyy년 MM월 dd일", "dd-MM-yyyy"
        };

        for (String pattern : patterns) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateStr, fmt);
            } catch (Exception e) {
                // 무시
            }
        }

        throw new IllegalArgumentException("지원하지 않는 날짜 형식: " + dateStr);
    }
}