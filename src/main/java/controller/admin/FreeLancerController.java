package controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/freelancer")
public class FreeLancerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeLancerController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerid = request.getParameter("freelancerid");


//        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("/admin/freelancer_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
