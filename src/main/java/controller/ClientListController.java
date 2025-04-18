package controller;

import dto.AdminProject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/client-list")
public class ClientListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String comapnyid = request.getParameter("companyid");
        List<AdminProject> settlements = new ArrayList<>();

        request.setAttribute("settlements", settlements);
        request.getRequestDispatcher("/admin/client_project_list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
