package controller.admin;

import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dto.AdminProject;
import dto.AdminProjectDetail;
import dto.Contract;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
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
            if (contractIdParam != null) {
                // 👉 정산내역 페이지 (settlement_info.jsp)
                int contractId = Integer.parseInt(contractIdParam);
//                AdminProjectDetail detail = settlementDAO.selectSettlementInfoByContractId(contractId);
//                request.setAttribute("settlementInfo", detail);
                request.getRequestDispatcher("/admin/settlement_info.jsp").forward(request, response);
            } else if (slistIdParam != null) {
                // 👉 정산하기 페이지 (settlement_detail.jsp)
                int projectId = Integer.parseInt(slistIdParam);

//                request.setAttribute("contractList", contractList);
                request.getRequestDispatcher("/admin/settlement_detail.jsp").forward(request, response);
            } else {
                // 👉 기본 목록 페이지 (settlement.jsp)
                List<AdminProject> projectList = settlementDAO.selectProjectsForSettlement();
                request.setAttribute("projectList", projectList);
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
