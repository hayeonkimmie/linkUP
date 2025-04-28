package controller.freelancer;

import service.freelancer.IUserService;
import service.freelancer.UserService;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/check-nickname")
public class CheckNicknameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckNicknameServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IUserService service = new UserService();
        String nickname = request.getParameter("nickname");

        boolean isAvailable = service.isNicknameAvailable(nickname);
        System.out.println("isAvailable" + isAvailable);
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(Boolean.toString(isAvailable));
    }
}