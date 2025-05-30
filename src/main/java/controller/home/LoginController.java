package controller.home;

import dao.client.ClientDAOImpl;
import dao.common.UserDAO;
import dto.Freelancer;
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

        // role에 따라 인증 분기
        if ("recruiter".equals(role)) {
            isAuthenticated = authenticateRecruiter(id, password);
        } else if ("jobseeker".equals(role)) {
            isAuthenticated = authenticateJobseeker(id, password);
        }

        if (isAuthenticated) {
            // 로그인 성공 시 세션에 저장
            HttpSession session = request.getSession();
            session.setAttribute("userId", id);
            session.setAttribute("role", role); // 여기서 role 저장
            // password 저장은 제거
            // session.setAttribute("password", password); <-- 이거 절대 저장하지 않기
            if ("recruiter".equals(role)) {
                session.setAttribute("client_id", id);
            }


            response.sendRedirect(request.getContextPath() + "/mainPage");
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
