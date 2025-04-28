package controller.admin;

import dao.admin.ContractDAO;
import dao.admin.ProjectDAO;
import dao.admin.SettlementDAO;
import dto.AdminSettleHistory;
import dto.AdminSettleHistorySummary;
import service.admin.ISettlementService;
import service.admin.SettlementService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/settlement-history")
public class SettleHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;



    private final ISettlementService settlementService = new SettlementService(
            new ContractDAO(), new ProjectDAO(), new SettlementDAO()
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        int curPage = 1;
        int listCount = 10;
        try {
            curPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException ignored) {}

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(curPage);

        int totalCount = 0;
        try {
            totalCount = settlementService.countHistory(keyword, startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int allPage = (int) Math.ceil((double) totalCount / listCount);
        int startPage = ((curPage - 1) / 10) * 10 + 1;
        int endPage = Math.min(startPage + 9, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        int offset = (curPage - 1) * listCount;
        List<AdminSettleHistorySummary> list = List.of();
        try {
            list = settlementService.selectHistorySummaryList(keyword, startDate, endDate, offset, listCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(AdminSettleHistorySummary s : list) {
            System.out.println("s = " + s);
        }
        request.setAttribute("settlementList", list);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("totalCount", totalCount);
        request.getRequestDispatcher("/admin/settle_history.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
