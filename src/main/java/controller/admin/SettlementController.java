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

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
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
        request.setCharacterEncoding("UTF-8");
        IContractDAO contractDAO = new ContractDAO();
        IProjectDAO projectDAO = new ProjectDAO();
        ISettlementDAO settlementDAO = new SettlementDAO();

        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String jsonData = request.getParameter("jsonData");

        Gson gson = new Gson();
        try {
            PrepareSettleJson[] item = gson.fromJson(jsonData, PrepareSettleJson[].class);
            PrepareSettleJson psJson = (PrepareSettleJson) item[0];
            AdminPrepareSettle prepareSettle = contractDAO.selectInfoForSettleById(item[0].getId());
            AdminProjectDetail project = projectDAO.selectProjectDetail(projectId);
            // 정산 회차 만들기
            int maxCnt = settlementDAO.getMaxCntByContractId(psJson.getId()); // e.g. MyBatis 매퍼 호출
            int newCnt = maxCnt + 1;

            System.out.println(psJson);

            Settlelist settlelist = new Settlelist(
                    psJson.getId(), // contractId
                    Integer.parseInt(prepareSettle.getPosition()),
                    prepareSettle.getClientId(),
                    project.getProjectName(),
                    item[0].getAmount(),
                    Date.valueOf(item[0].getSettleDate()),
                    newCnt
            );

            for (PrepareSettleJson p : item) {
                AdminPrepareSettle aprepareSettle = contractDAO.selectInfoForSettleById(p.getId());

//                Settlement settlement = new Settlement(
//                        settlelist.getSlistId(), // 정산 회차 ID (방금 insert된 값)
//                        Integer.parseInt(aprepareSettle.getPosition()), // categoryId or projectPayId
//                        aprepareSettle.getClientId(),
//                        project.getProjectName(),
//                        p.getAmount(),
//                        Date.valueOf(p.getStart()),
//                        Date.valueOf(p.getEnd()),
//                        Integer.parseInt(request.getParameter("settleDate")), // 전달된 정산일
//                        aprepareSettle.getPosition(), // 직급명 or 포지션명
//                        aprepareSettle.getName(), // 이름
//                        "미지급", // 초기 상태
//                        ""
////                        aprepareSettle.getAccount() // 계좌번호 (account 필드가 있다면)
//                );

//                settlementDAO.insertSettlement(settlement);
            }

            settlementDAO.createSettlelist(settlelist);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/admin/project");
    }
}
