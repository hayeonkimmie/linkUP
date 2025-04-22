/**
 * LoginController.java
 * GET : /admin/login admin/login.jsp 렌더링
 * POST : /admin/login 로그인 처리 -- 추후 로그인 처리 로직 추가 --
 */

package controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        request.getRequestDispatcher("/admin/dashboard").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
