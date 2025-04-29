package controller.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/myPage")
public class MyPageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        System.out.println(role);
        if(role.equals("jobseeker")){
            request.getRequestDispatcher("./freelancer/my_page_main.jsp").forward(request,response);

        } else if(role.equals("recruiter")) {
            request.getRequestDispatcher("./client/profileSetting.jsp").forward(request,response);

        } else {
            request.getRequestDispatcher("./error.jsp").forward(request,response);
        }
    }

}
