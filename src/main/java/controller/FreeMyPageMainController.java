package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page")
public class FreeMyPageMainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageMainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        session.setAttribute("id", id);
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }
}