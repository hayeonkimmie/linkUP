package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/news")
public class HomeNewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeNewsController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./home/news.jsp").forward(request, response);

    }


}
