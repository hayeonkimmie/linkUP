package controller.home;

import dao.common.IUserDAO;
import dao.common.UserDAO;
import dto.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/createAccJobseeker")
public class CreateAccJobseekerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccJobseekerController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("./home/createAccJobseeker.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUserId(id);
        user.setName(id);  // 구직자는 이름 대신 아이디로 등록하거나 별도 필드 나중에 추가 가능
        user.setNickname(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNum(phone);
        user.setProfileImg(null);
        user.setToken(null);
        user.setAcceptNoti(true);
        user.setAcceptConsent(true);
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));
        user.setWithdrawalDate(null);

        IUserDAO userDAO = new UserDAO();
        int result = userDAO.insertUser(user);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
        }
    }

}
