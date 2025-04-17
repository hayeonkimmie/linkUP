package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/jjim-projs-list")
public class JJimProjsList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JJimProjsList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/freelancer/my_page_my_jjim_project.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/freelancer/my_page_my_jjim_project.jsp").forward(request, response);
    }
}