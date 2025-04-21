package controller;

import dao.client.ClientDAOImpl;
import dao.common.UserDAO;
import dto.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAO();          // User 테이블 DAO
    private ClientDAOImpl clientDAO = new ClientDAOImpl(); // Client 테이블 DAO

    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./home/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean isAuthenticated = false;

        if ("recruiter".equals(role)) {
            isAuthenticated = authenticateRecruiter(id, password);
        } else if ("jobseeker".equals(role)) {
            isAuthenticated = authenticateJobseeker(id, password);
        }

        if (isAuthenticated) {
            // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("userId", id);
            session.setAttribute("role", role);

            response.sendRedirect(request.getContextPath() + "/mainLoginVersion");
        } else {
            // 로그인 실패 (에러 메시지 전달)
            request.setAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
            request.getRequestDispatcher("/home/login.jsp").forward(request, response);
        }
    }

    // 사업자 인증
    private boolean authenticateRecruiter(String id, String password) {
        User user = userDAO.selectUserById(id);
        boolean isClient = clientDAO.existsClientById(id); // client 테이블에 있는지 확인

        return (user != null && password.equals(user.getPassword()) && isClient);
    }

    // 일반 사용자 인증
    private boolean authenticateJobseeker(String id, String password) {
        User user = userDAO.selectUserById(id);
        boolean isClient = clientDAO.existsClientById(id); // client 테이블에 있는지 확인

        return (user != null && password.equals(user.getPassword()) && !isClient);
    }
}
