package controller.client;

import dto.ProjectMgt;
import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;

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

    public ClientRecruitMgtList() {
        super();
    }

    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 세션에서 로그인 한 clientId가져오기
            HttpSession session = request.getSession();
            String clientId = (String) session.getAttribute("userId");

            if (clientId == null) {
                clientId = "client001"; //테스트용 기본값
            }

            // 파라미터로 status받기 (전체보기, 구인중, 완료, 임시저장)
            String status = request.getParameter("status");
            if (status == null || status.isEmpty()) {  //디폴트는 전체보기
                status = "all";
            }

            // param 구성
            Map<String, Object> param = new HashMap<>();
            param.put("clientId", clientId);
            param.put("status", status); // 'all'포함

            // 서비스 호출
            List<ProjectMgt> projectList = service.getProjectByStatus(param);

            // 날짜 기반 진행상태 세팅 추가
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 날짜 기반 진행상태 세팅
            for (ProjectMgt projectMgt : projectList) {
                try {
                    String startStr = projectMgt.getStartDate();
                    String endStr = projectMgt.getEndDate();

                    if (startStr != null && endStr != null) {
                        LocalDate start = LocalDate.parse(startStr, formatter);
                        LocalDate end = LocalDate.parse(endStr, formatter);

                        if (today.isBefore(start)) {
                            projectMgt.setProjectProgress("시작전");
                        } else if (today.isAfter(end)) {
                            projectMgt.setProjectProgress("종료됨");
                        } else {
                            projectMgt.setProjectProgress("진행중");
                        }
                    }

                    // 상태가 "모집완료"가 아닌 경우 projectProgress 비우기
                    if (!"구인완료".equals(projectMgt.getStatus())) {
                        projectMgt.setProjectProgress("");
                    }

                } catch (Exception e) {
                    System.err.println("날짜 파싱 실패: " + e.getMessage());
                    projectMgt.setProjectProgress("");  // "알수없음" 대신 공백 처리
                }
            }


            // 결과 저장해서 JSP로 전달
            request.setAttribute("projectList", projectList); //서비스 호출해서 list받아오기
            request.setAttribute("status", status); // 상태 값 받아오기
            System.out.println("projectList.size() = " + projectList.size()); // 값 받아오는지 테스트
            for (ProjectMgt projectMgt : projectList) {
                System.out.println("startDate = " + projectMgt.getStartDate());
                System.out.println("endDate = " + projectMgt.getEndDate());

                System.out.println("status = " + projectMgt.getStatus());
                System.out.println("progress = " + projectMgt.getProjectProgress());
            }




            request.getRequestDispatcher("./client/recruitmentList.jsp").forward(request, response);

        } catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 조회 중 오류 발생");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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
                // skip
            }
        }

        throw new IllegalArgumentException("지원하지 않는 날짜 형식: " + dateStr);
    }
}

