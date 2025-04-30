package controller.admin;
import com.google.gson.Gson;
import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dto.SettlementDetailDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/settlement-detail")
public class SettlementDetailModalController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final SettlementDAO settlementDAO = new SettlementDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String freelancerName = request.getParameter("freelancerName"); // 🔥 수정
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        try {
            List<SettlementDetailDTO> settlementList = settlementDAO.selectSettlementHistory(freelancerName, projectId);
            response.setContentType("application/json; charset=UTF-8");
            new Gson().toJson(settlementList, response.getWriter());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
