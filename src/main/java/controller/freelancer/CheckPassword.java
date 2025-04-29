package controller.freelancer;

import service.freelancer.IUserService;
import service.freelancer.UserService;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/check-password")
public class CheckPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckPassword() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String sessionId = (String) request.getSession().getAttribute("userId");
        String password = request.getParameter("password");
        sessionId ="free002";
        System.out.println(password);
        IUserService service = new UserService();
        boolean isCorrect = service.checkPassword(sessionId, password);

        System.out.println(isCorrect);
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(Boolean.toString(isCorrect));
    }
}