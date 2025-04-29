package controller.admin;

import com.google.gson.Gson;
import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dto.SettledInfoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/admin/settlementMonth")
public class SettlementMonthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SettlementMonthController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        ISettlementDAO settlementDAO = new SettlementDAO();

        try {
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            Integer cnt = Integer.parseInt(request.getParameter("cnt")); // ✅ cnt로 받는다

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("projectId", projectId);
            paramMap.put("cnt", cnt);

            List<SettledInfoDTO> doneList = settlementDAO.selectSettledFreelancersByMonth(paramMap);
            List<SettledInfoDTO> waitList = settlementDAO.selectWaitingFreelancersByMonth(paramMap);

            int totalAmount = 0;
            for (SettledInfoDTO dto : doneList) totalAmount += dto.getSettleAmount();
            for (SettledInfoDTO dto : waitList) totalAmount += dto.getSettleAmount();

            Map<String, Object> result = new HashMap<>();
            result.put("doneList", doneList);
            result.put("waitList", waitList);
            result.put("totalAmount", totalAmount);

            String json = new Gson().toJson(result);
            System.out.println(json);
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"데이터 조회 실패\"}");
        }
    }
}
