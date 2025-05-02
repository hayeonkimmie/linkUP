package controller.client;

import dao.client.ClientCandidateMgtDAOImpl;
import service.client.ClientCandidateMgtImpl;
import service.client.IClientCandidateMgt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/candidateAction")
public class ClientCandidateAction extends HttpServlet {
    // 객체 변경사항에 대한 호환성을 유지하기 위해 명시적으로 설정
    private static final long serialVersionUID = 1L;

    // 서비스 계층에 비즈니스 로직 위임
    private final IClientCandidateMgt service = new ClientCandidateMgtImpl();

    // 서비릇 초기화
    public ClientCandidateAction() {
        super(); // 부모클래스 HttpServlet 실행
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // 아래 projectId, freelancerId, applyStatus : 클라이언트가 폼/AJAX로 전송하기 위한 데이터
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            String freelancerId = request.getParameter("freelancerId");
            int applyStatus = Integer.parseInt(request.getParameter("applyStatus"));

            // 서비스 계층 메서드 호출 (프리랜서 지원상태 업데이트)
            service.updateApplyStatus(projectId, freelancerId, applyStatus);
            response.setStatus(HttpServletResponse.SC_OK); // http응답상태 코드 200 ok

            // 수락(1)일 때만 계약 테이블에 insert
            if (applyStatus == 1) {
                service.insertContract(projectId, freelancerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 클라이언트에게 500 상태코드 & 오류메시지 전송
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "지원자 상태 업데이트 실패");
        }
    }
}
