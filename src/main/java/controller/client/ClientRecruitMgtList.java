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

    public ClientRecruitMgtList() {
        super();
    }

    // 서비스 호출 준비
    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // [오늘 날짜를 기준으로 DB 상태 갱신]
            Map<String, Object> statusParam = new HashMap<>();
            statusParam.put("today", new java.sql.Date(System.currentTimeMillis()));
            service.updateProgressToOngoing(statusParam); // 시작전 -> 진행중
            service.updateProgressToEnd(statusParam); // 진행중 -> 종료됨

            // 세션에서 로그인 한 clientId 확인해서 가져오기
            HttpSession session = request.getSession();
            String clientId = (String) session.getAttribute("userId");
            if (clientId == null || clientId.isEmpty()) {
                clientId = "client001"; // 테스트용 기본값
            }

            // 페이징 처리
            String pageStr = request.getParameter("page");
            Integer page = (pageStr == null) ? 1: Integer.parseInt(pageStr);

            // 페이징 정보 설정 - 수정: rowCount를 6으로 증가시켜 페이지당 프로젝트 수 늘림
            int rowCount = 3; // 한 페이지당 표시할 항목 수 (3개로 유지)
            int startRow = (page - 1) * rowCount; // 시작 행 계산

            PageInfo pageInfo = new PageInfo(page);

            // 파라미터로 status받기 (전체보기, 구인중, 시작전, 진행중, 종료됨)
            String status = request.getParameter("status");
            if (status == null || status.isEmpty()) {
                status = "all"; // 기본값: 전체보기
            }

            // 상태값을 XML 매핑을 위해 변환
            String statusValue = null; // 전체를 위해 null로 시작

             //프로젝트 상태변수
            String projectProgress = null;

            // 상태별 필터링 처리
            if ("open".equals(status)) {
                statusValue = "구인중";
            } else if ("done-start".equals(status)) {
                statusValue = "구인완료";
                projectProgress = "시작전";
            } else if ("done-progress".equals(status)) {
                statusValue = "구인완료";
                projectProgress = "진행중";
            } else if ("done-end".equals(status)) {
                statusValue = "구인완료";
                projectProgress = "종료됨";
            } else if (!"all".equals(status)) {
                status = "all"; // 잘못된 값이면 전체보기 처리
            }

            // param 구성 및 DB조회
            Map<String, Object> param = new HashMap<>();
            param.put("clientId", clientId);
            param.put("startRow", startRow);
            param.put("rowCount", rowCount);

            if (statusValue != null) {
                param.put("status", statusValue);
            }
            if (projectProgress != null) {
                param.put("projectProgress", projectProgress); // 구인완료 하위 필터일 때만 추가
            }

            // 전체 프로젝트 수 조회 (페이징을 위해)
            int totalProjects = service.getProjectCountByStatus(param);

            // 페이징 정보 업데이트
            pageInfo.setAllPage((int) Math.ceil((double) totalProjects / rowCount));
            pageInfo.setStartPage(((page - 1) / 5) * 5 + 1);
            pageInfo.setEndPage(Math.min(pageInfo.getStartPage() + 4, pageInfo.getAllPage()));
            request.setAttribute("pageInfo", pageInfo);

            // 서비스 호출해서 프로젝트 목록 불러오기
            List<ProjectMgt> projectList = service.getProjectByStatus(param);

            // 리스트 처리 개선: 빈 리스트 확인
            if (projectList.isEmpty()) {
                System.out.println("조회된 프로젝트가 없습니다: status=" + status);
            }

            // 결과 저장해서 JSP로 전달
            request.setAttribute("projectList", projectList);
            request.setAttribute("status", status);

            System.out.println("projectList.size() = " + projectList.size());

            // JSP로 이동
            request.getRequestDispatcher("./client/recruitmentList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 조회 중 오류 발생: " + e.getMessage());
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