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
import java.time.LocalDate;
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
        // 응답 형식 설정
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 모집확정 버튼 누르면 해당 projectId 받아오기
        String projectIdParam = request.getParameter("projectId"); // projectId검증

        if (projectIdParam == null || projectIdParam.isEmpty()) { // projectId 값이 없으면
            // JSON 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Project ID가 없습니다.");

            response.getWriter().write(new Gson().toJson(result));
            return; // 오류메시지 보내고 종료
        }

        try {
            int projectId = Integer.parseInt(projectIdParam); // 문자열 → int 변환

            // 중요: 현재 날짜를 년도 상관없이 설정 (시스템 날짜 사용)
            LocalDate currentDate = LocalDate.now();
            // 연도는 유지하되 월과 일만 사용
            int currentMonth = currentDate.getMonthValue();
            int currentDay = currentDate.getDayOfMonth();

            // 로그에 실제 오늘 날짜 출력
            System.out.println("실제 오늘 날짜: " + currentDate);

            // 서비스에 전달할 날짜 객체 (년도는 무관)
            Date today = Date.valueOf(LocalDate.of(2025, currentMonth, currentDay));

            // 디버깅 정보
            System.out.println("모집확정 요청 - 프로젝트 ID: " + projectId + ", 사용할 날짜: " + today);

            // 확정 직후의 상태를 확인하기 위해 해당 프로젝트 정보를 단일 조회
            ProjectMgt project = service.getProjectById(projectId);

            if (project == null) { // 해당 프로젝트가 없으면
                Map<String, Object> result = new HashMap<>();
                result.put("success", false);
                result.put("message", "해당 프로젝트를 찾을 수 없습니다.");

                response.getWriter().write(new Gson().toJson(result));
                return;
            }

            // 디버깅 정보
            System.out.println("프로젝트 정보 - 제목: " + project.getTitle() +
                    ", 시작일: " + project.getStartDate() +
                    ", 종료일: " + project.getEndDate());

            String startDateStr = project.getStartDate();
            String projectProgress = "시작전"; // 기본값

            if (startDateStr != null && !startDateStr.isEmpty()) {
                try {
                    // 시작일 파싱
                    Date startDate = Date.valueOf(startDateStr);

                    // 연도에 관계없이 월과 일만 비교하기 위해 변환
                    LocalDate startLocalDate = startDate.toLocalDate();
                    LocalDate effectiveStartDate = LocalDate.of(2025, startLocalDate.getMonthValue(), startLocalDate.getDayOfMonth());
                    Date effectiveStartDateSql = Date.valueOf(effectiveStartDate);

                    // 디버깅 정보 (실제 비교에 사용할 날짜)
                    System.out.println("날짜 비교(연도 무관) - 오늘: " + today +
                            ", 시작일(원래): " + startDate +
                            ", 시작일(변환): " + effectiveStartDateSql);

                    // 월/일만 비교
                    System.out.println("today.before(effectiveStartDate): " + today.before(effectiveStartDateSql));

                    // 연도 무시하고 월/일만 비교
                    if (!today.before(effectiveStartDateSql)) {
                        // today가 startDate랑 같거나 이후면 모집확정 금지
                        Map<String, Object> result = new HashMap<>();
                        result.put("success", false);
                        result.put("message", "프로젝트 시작일 이전까지만 모집확정이 가능합니다. 프로젝트 기간을 수정해주세요.");

                        response.getWriter().write(new Gson().toJson(result));
                        return; // 확정 중단
                    }

                    // (정상) 모집확정 가능
                    projectProgress = "시작전";
                } catch (IllegalArgumentException e) {
                    // 날짜 형식이 잘못된 경우
                    System.out.println("날짜 변환 오류: " + e.getMessage());
                    Map<String, Object> result = new HashMap<>();
                    result.put("success", false);
                    result.put("message", "프로젝트 시작일 형식이 올바르지 않습니다: " + e.getMessage());

                    response.getWriter().write(new Gson().toJson(result));
                    return;
                }
            }

            // 시작일 검사 통과 후 모집확정 처리(Map에 오늘 날짜 + 프로젝트 ID 같이 넘겨서 맵에 담아주기)
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("today", today);

            // 서비스 호출해서 프로젝트 상태를 '구인완료' + '시작전'으로 업데이트 (모집확정 처리)
            service.updateStatusToConfirmed(param);

            // 디버깅 정보
            System.out.println("모집확정 완료: projectId=" + projectId + ", 상태=" + projectProgress);

            // JSON 성공 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("projectProgress", projectProgress);

            response.getWriter().write(new Gson().toJson(result));

        } catch (NumberFormatException e) {
            // 프로젝트 ID가 숫자 형식이 아닌 경우
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "잘못된 프로젝트 ID 형식입니다.");

            response.getWriter().write(new Gson().toJson(result));
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생해도 JSON 형태로 실패 응답
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "구인 확정 중 서버 오류가 발생했습니다: " + e.getMessage());

            response.getWriter().write(new Gson().toJson(result));
        }
    }
}