package controller.freelancer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/project-status")
public class MyProjectStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyProjectStatus() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        if(status == null || status.isEmpty() || status.equals("curr")) {

        } else {

        }

        request.getRequestDispatcher("/freelancer/my_project_status.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}