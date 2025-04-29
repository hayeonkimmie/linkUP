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
import java.util.Map;

// 구인자 마이페이지 '내 프로젝트 조회'에서 구인중 > 구인확정 누르면 구인중에 있던 리스트가 '시작전'으로 넘어감
@WebServlet("/confirmRecruitment")
public class ConfirmRecruitment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConfirmRecruitment() {
        super();
    }

    // service호출을 위해 선언
    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    // 데이터 전송하는 것이므로 doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모집확정 버튼 누르면 해당 projectId 받아오기
        String projectIdParam = request.getParameter("projectId"); // projectId검증

        if (projectIdParam == null) { // projectId 값이 없으면
            // 수정: sendError 대신 JSON 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Project ID가 없습니다.");

            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));
            return; // 오류메시지 보내고 종료
        }

        try {
            int projectId = Integer.parseInt(projectIdParam); // 문자열 → int 변환
            Date today = new java.sql.Date(System.currentTimeMillis()); // 오늘일자

            // 확정 직후의 상태를 확인하기 위해 해당 프로젝트 정보를 단일 조회
            ProjectMgt project = service.getProjectById(projectId); // (수정) 하나만 조회

            if (project == null) { // 해당 프로젝트가 없으면
                // 수정: sendError 대신 JSON 응답
                Map<String, Object> result = new HashMap<>();
                result.put("success", false);
                result.put("message", "해당 프로젝트를 찾을 수 없습니다.");

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new Gson().toJson(result));
                return;
            }

            String startDateStr = project.getStartDate();
            String endDateStr = project.getEndDate();
            String projectProgress = "시작전"; // 기본값

            if (startDateStr != null) {
                Date startDate = Date.valueOf(startDateStr);

                // 수정된 조건 (today가 startDate보다 무조건 앞에 있어야 함)
                if (!today.before(startDate)) {
                    // today가 startDate랑 같거나 이후면 모집확정 금지
                    Map<String, Object> result = new HashMap<>();
                    result.put("success", false);
                    result.put("message", "프로젝트 시작일 이전까지만 모집확정이 가능합니다. 프로젝트 기간을 수정해주세요.");

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(new Gson().toJson(result));
                    return; // 확정 중단
                }

                // (정상) 모집확정 가능
                projectProgress = "시작전";
            }

            // 시작일 검사 통과 후 모집확정 처리(Map에 오늘 날짜 + 프로젝트 ID 같이 넘겨서 맵에 담아주기)
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("today", today);

            // 서비스 호출해서 프로젝트 상태를 '구인완료' + '시작전'으로 업데이트 (모집확정 처리)
            service.updateStatusToConfirmed(param); // 서비스 호출

            // JSON 성공 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("projectProgress", projectProgress);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));

            System.out.println("모집확정 완료: projectId=" + projectId + ", 상태=" + projectProgress);

        } catch (Exception e) {
            e.printStackTrace();
            // 수정: 에러 발생해도 JSON 형태로 실패 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "구인 확정 중 서버 오류가 발생했습니다.");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(result));
        }
    }
}
