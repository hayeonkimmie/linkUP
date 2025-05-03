package controller.home;

import dto.Client;
import dto.User;
import service.home.CreateAccRecruiter;
import service.home.ICreateAccRecruiter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/createAccRecruiter")
public class CreateAccRecruiterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ICreateAccRecruiter createAccRecruiter = new CreateAccRecruiter();

    public CreateAccRecruiterController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원가입 화면으로 이동
        request.getRequestDispatcher("./home/createAccRecruiter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 데이터 인코딩
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 받아오기
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String companyName = request.getParameter("company");
        String bizNumber = request.getParameter("bizNumber");

        // User 객체 생성
        User user = new User();
        user.setUserId(id);
        user.setName(companyName);
        user.setNickname(companyName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNum(phone);
        user.setProfileImg(null);
/*        user.setToken(null);
        user.setAcceptNoti(true);
        user.setAcceptConsent(true);*/
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));
        user.setWithdrawalDate(null);

        // Client 객체 생성
        Client client = new Client();
        client.setClientId(id);
        client.setCompanyRegNo(bizNumber);
        client.setCompanyName(companyName);
        client.setCompanyPhoneNumber (null);
        client.setCompanyAddress(null);
        client.setCompanyWebsiteUrl(null);
        client.setCompanyDescription(null);
        client.setCompanyFaxNum(null);

        // 회원가입 처리
        boolean isSuccess = createAccRecruiter.registerRecruiter(user, client);

        if (isSuccess) {
            // 회원가입 성공 ➔ 메인 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // 회원가입 실패 ➔ 에러 페이지로 이동
            response.sendRedirect(request.getContextPath() + "./error/error.jsp");
        }
    }
}
