/**
 * SettlementController.java
 * settlement.jsp, settlement_detail.jsp, settlement_info.jsp
 * GET : 파라미터에 따라 정산 대상 프로젝트 목록, 정산 내역, 정산하기 페이지를 라우팅
 * POST : 정산하기 페이지에서 받은 폼 데이터를 기반으로 정산 내역을 테이블에 저장 및 정산 내역으로 리다이렉트
 */
package controller.admin;

import com.google.gson.Gson;
import dao.admin.*;
import dto.*;
import service.admin.ISettlementService;
import service.admin.SettlementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        ISettlementService settlementService = new SettlementService(null,null,settlementDAO);
        String contractIdParam = request.getParameter("contractid");
        String slistIdParam = request.getParameter("slistid");

        try {
            HashMap<Integer, AdminSettleProject> projectList;
            if (slistIdParam != null) {
                // 👉 정산내역 페이지 (settlement_info.jsp)
                int contractId = Integer.parseInt(slistIdParam);
                System.out.println("contractId : " + contractId);
                request.getRequestDispatcher("/admin/settlement_info.jsp").forward(request, response);
            } else if (contractIdParam != null) {
                // 👉 정산하기 페이지 (settlement_detail.jsp)
                int projectId = Integer.parseInt(contractIdParam);
                int cnt = 1;
                int totalAmount = 0;
                List<AdminSettleTarget> targetList = settlementDAO.selectFreelancersForSettlement(projectId, cnt);
                for (AdminSettleTarget t : targetList) {
                    System.out.println("Target : " + t);
                    totalAmount += t.getTotalPay();
                }
                projectList = request.getSession().getAttribute("projectList") != null ?
                        (HashMap<Integer, AdminSettleProject>) request.getSession().getAttribute("projectList") : new HashMap<>();
                if(projectList.get(projectId) == null){
                    projectList = settlementDAO.selectProjectsForSettlement();
                }
                AdminSettleProject selected = projectList.get(projectId);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("targetList", targetList);
                request.setAttribute("project", selected);
                request.getRequestDispatcher("/admin/settlement_detail.jsp").forward(request, response);
            } else {
                projectList = settlementDAO.selectProjectsForSettlement();
                projectList = settlementService.filterProjectsWithUnsettled(projectList);
                for (AdminSettleProject p : projectList.values()) {
                    System.out.println("Project : " + p);
                }
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
        request.setCharacterEncoding("UTF-8");

        IContractDAO contractDAO = new ContractDAO();
        IProjectDAO projectDAO = new ProjectDAO();
        ISettlementDAO settlementDAO = new SettlementDAO();
        SettlementService settlementService = new SettlementService(contractDAO, projectDAO, settlementDAO);
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String jsonData = request.getParameter("jsonData");
        Gson gson = new Gson();
        Settlelist settlelist = null;
        try {
            PrepareSettleJson[] item = gson.fromJson(jsonData, PrepareSettleJson[].class);
            settlelist = settlementService.createSettleList(item[0], projectId);
            if (settlelist == null) {
                System.out.println("정산 생성 조건 미충족으로 정산 중단됨");
                response.sendRedirect("/admin/settlement");
                return;
            }
            settlementService.createSettlement(settlelist, item, projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin/settlement_info?slist_id=" +  Objects.requireNonNull(settlelist).getSlistId());
    }
}
