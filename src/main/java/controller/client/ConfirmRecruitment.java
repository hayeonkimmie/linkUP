package controller.client;

import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;
import com.google.gson.Gson;
import dto.ProjectMgt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 구인자 마이페이지 '내 프로젝트 조회'에서 구인중 > 구인확정 누르면 구인중에 있던 리스트가 '시작전'으로 넘어감
@WebServlet("/confirmRecruitment")
public class ConfirmRecruitment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConfirmRecruitment() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // service호출을 위해 선언
    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    // 데이터 전송하는 것이므로 doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectIdParam = request.getParameter("projectId"); // projectId검증

        if (projectIdParam == null) { // projectId 값이 없으면
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Project ID 없음!");
            return; // 오류메시지 보내고 종료
        }

        try {
            int projectId = Integer.parseInt(projectIdParam); // 문자열 → int 변환
            Date today = new java.sql.Date(System.currentTimeMillis());

            // Map 구성 (오늘 날짜 + 프로젝트 ID 같이 넘김)
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("today", today);

            service.updateStatusToConfirmed(param); // 서비스 호출

            // 확정 직후의 상태를 확인하기 위해 다시 해당 프로젝트 정보를 조회
            Map<String, Object> checkParam = new HashMap<>();
            checkParam.put("clientId", (String) request.getSession().getAttribute("userId")); // 현재 로그인한 유저의 clientId
            checkParam.put("status", "구인완료"); // 확정된 프로젝트만 대상

            List<ProjectMgt> projectList = service.getProjectByStatus(checkParam);

            String projectProgress = "시작전"; // 기본값

            for (ProjectMgt project : projectList) {
                if (project.getProjectId() == projectId) {
                    String startDateStr = project.getStartDate();
                    if (startDateStr != null) {
                        Date startDate = Date.valueOf(startDateStr);
                        if (!startDate.after(today)) {
                            projectProgress = "진행중";
                        }
                    }
                    break;
                }
            }

            // JSON 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("projectProgress", projectProgress);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));

            System.out.println("모집확정 완료: projectId=" + projectId + ", 상태=" + projectProgress);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "구인 확정 중 오류 발생");
        }
    }
}
