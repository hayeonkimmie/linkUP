package controller.admin;

import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/admin/settlement")
public class SettlementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SettlementController() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ISettlementDAO settlementDAO = new SettlementDAO();
        String contractIdParam = request.getParameter("contractid");
        String slistIdParam = request.getParameter("slistid");

        try {
            HashMap<Integer, AdminProject> projectList;
            if (contractIdParam != null) {
                // 👉 정산내역 페이지 (settlement_info.jsp)
                int contractId = Integer.parseInt(contractIdParam);


                request.getRequestDispatcher("/admin/settlement_info.jsp").forward(request, response);
            } else if (slistIdParam != null) {
                // 👉 정산하기 페이지 (settlement_detail.jsp)
                int projectId = Integer.parseInt(slistIdParam);
                System.out.println("projectId : " + projectId);
                int cnt = 1;
                int totalAmount = 0;
                List<AdminSettleTarget> targetList = settlementDAO.selectFreelancersForSettlement(projectId, cnt);
                for (AdminSettleTarget t : targetList) {
                    totalAmount += t.getTotalPay();
                }
                projectList = request.getSession().getAttribute("projectList") != null ?
                        (HashMap<Integer, AdminProject>) request.getSession().getAttribute("projectList") : new HashMap<>();
                if(projectList.get(projectId) == null){
                    projectList = settlementDAO.selectProjectsForSettlement();
                }
                AdminProject selected = projectList.get(projectId);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("targetList", targetList);
                request.setAttribute("project", selected);
                System.out.println("selected Project : \n"+selected);
                request.getRequestDispatcher("/admin/settlement_detail.jsp").forward(request, response);
            } else {
                // 👉 기본 목록 페이지 (settlement.jsp)
                projectList = settlementDAO.selectProjectsForSettlement();
                request.setAttribute("projectList", projectList);
                request.getSession().setAttribute("projectList", projectList);
                request.getRequestDispatcher("/admin/settlement.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "정산 페이지 처리 중 오류 발생");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
