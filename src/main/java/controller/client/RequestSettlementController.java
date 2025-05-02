package controller.client;

import dao.client.ClientSettlementDAO;
import dao.client.IClientSettlementDAO;
import dto.ClientSettleTarget;
import service.client.ClientSettleService;
import service.client.IClientSettleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/request-settlement")
public class RequestSettlementController extends HttpServlet {

    private final IClientSettlementDAO clientSettlementDAO = new ClientSettlementDAO();
    private final IClientSettleService settleService = new ClientSettleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            String clientId = (String) request.getSession().getAttribute("userId");
            if (clientId == null) clientId = "client005"; // fallback for testing

            // 정산 대상 목록 조회
            List<ClientSettleTarget> settleTargets = clientSettlementDAO.selectSettleTargetsByClient(clientId, projectId);

            // 정산 회차 판단 로직
            int nextRound = settleService.getNextSettleRound(projectId);

            System.out.println("nextRound : "+nextRound);
            // 값 전달
            request.setAttribute("settleTargets", settleTargets);
            request.setAttribute("round", nextRound);
            request.getRequestDispatcher("/client/monthlyPayment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "정산 요청 처리 중 오류 발생");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 필요시 구현
    }
}