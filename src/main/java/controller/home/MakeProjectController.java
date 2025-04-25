package controller.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/makeProject")
public class MakeProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakeProjectController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("./home/makeProject.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
