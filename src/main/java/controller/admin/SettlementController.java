package controller.admin;

import dao.admin.ISettlementDAO;
import dao.admin.SettlementDAO;
import dto.AdminProject;

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
        request.setCharacterEncoding("utf-8");

        ISettlementDAO settlementDAO = new SettlementDAO();
        List<AdminProject> projectList = new ArrayList<>();

        try{
            projectList = settlementDAO.selectProjectsForSettlement();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("projectList", projectList);
            request.getRequestDispatcher("/admin/settlement.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
