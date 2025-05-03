package controller.client;

import dao.client.ClientCandidateMgtDAOImpl;
import service.client.ClientCandidateMgtImpl;
import service.client.IClientCandidateMgt;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/candidateMgt")
public class ClientCandidateMgt extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IClientCandidateMgt service = new ClientCandidateMgtImpl();

    public ClientCandidateMgt() {super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. 세션 및 기본 파라미터 읽기
            HttpSession session = request.getSession();
            String clientId = (String) session.getAttribute("userId"); // clientId 파라미터 받기

            //테스트용 기본값
            if (clientId == null || clientId.isEmpty()) {
                clientId = "client001";
            }

            // status 파라미터 받기
            String status = request.getParameter("status");
            if (status == null || status.isEmpty()) {
                status = "all"; //기본값 전체
            }

            // projectId 파라미터 받기 (정확하게 비교를 위해 int, 실 DB project_id는 int)
            // 2. 프로젝트 아이디 읽기 (NULL여부 검증)
            String projectIdStr = request.getParameter("projectId");
            int projectId = 0; // 초기화
            if (projectIdStr != null && !projectIdStr.isEmpty()) {
                projectId = Integer.parseInt(projectIdStr);
            } else {
                throw new IllegalArgumentException("projectId가 전달되지 않았습니다.");
            }

            // 3. 파라미터 모으기
            Map<String, Object> param = new HashMap<>();
            param.put("clientId", clientId);
            param.put("status", status);
            param.put("projectId", projectId);

            // 4. 서비스 호출해서 recruitment 클릭한 해당 프로젝트 가져오기
            dto.ClientCandidateMgt projectInfo = service.getProjectInfo(projectId);

            // 서비스 호출해서 지원자 목록 가져오기
            List<dto.ClientCandidateMgt> applicants = service.getCandidateMgtList(param);
            for(dto.ClientCandidateMgt applicant : applicants) {
                System.out.println(applicant);
            }

            // 5. 결과 저장해서 JSP로 값 전달
            request.setAttribute("project", projectInfo);
            request.setAttribute("applicants", applicants);
            request.getRequestDispatcher("./client/candidateMgt.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "지원자 조회 중 오류 발생");
        }
    }
}


