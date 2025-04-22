package controller.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 가져오기
        HttpSession session = request.getSession(false); // 기존 세션이 있으면 가져오고, 없으면 null

        if (session != null) {
            session.invalidate(); // 세션 무효화 (완전히 삭제)
        }

        // 로그아웃 후 메인 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/home/main.jsp");
    }
}
