package controller.client;

import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dao.client.ClientSettlementDAO;
import dao.client.IClientSettlementDAO;
import dto.*;
import service.ProjectService;
import service.client.ClientSettleService;
import service.client.IClientSettleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/request-settlement")
public class RequestSettlementController extends HttpServlet {

    private final IClientSettlementDAO clientSettlementDAO = new ClientSettlementDAO();
    private final IClientSettleService settleService = new ClientSettleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ProjectService projectService = new ProjectService();
        try {
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            String clientId = (String) request.getSession().getAttribute("userId");
            if (clientId == null) clientId = "client005"; // fallback for testing
            ISettlementDAO settlementDAO = new SettlementDAO();
            // 정산 대상 목록 조회
            List<ClientSettleTarget> settleTargets = clientSettlementDAO.selectSettleTargetsByClient(clientId, projectId);

            // 정산 회차 판단 로직
            int nextRound = settleService.getNextSettleRound(projectId);
            if(nextRound == 0) nextRound =1;
//            ProjectDetail project = projectService.selectProjectById(projectId);
            ClientProjectSummary project = settlementDAO.selectSettlementHistoryDetailByClientId(projectId);
            System.out.println("Project : "+project);
            // 값 전달
            // 예시 코드
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(project.getStartDate(), formatter);
            LocalDate end = LocalDate.parse(project.getEndDate(), formatter);
            Period period = Period.between(start.withDayOfMonth(1), end.withDayOfMonth(1)); // 월 단위 비교

            int months = period.getYears() * 12 + period.getMonths() + 1;
            request.setAttribute("projectMonthCount", months);
            request.setAttribute("settleTargets", settleTargets);
            request.setAttribute("projectInfo", project);
            request.setAttribute("round", nextRound);
            System.out.println("ProjectInfo : "+project);
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