package controller.home;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.client.ClientDAOImpl;
import dao.common.UserDAO;
import dto.Client;
import dto.User;
import service.common.KakaoLoginService;
import service.common.IKakaoLoginService;
import service.home.CreateAccRecruiter;
import service.home.ICreateAccRecruiter;

/**
 * Servlet implementation class KakaoLogin
 */
@WebServlet("/kakao-recruiter")
public class KakaoRecruiterLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ICreateAccRecruiter createAccRecruiter = new CreateAccRecruiter();

	public KakaoRecruiterLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDAO userDAO = new UserDAO();          // User 테이블 DAO
		ClientDAOImpl clientDAO = new ClientDAOImpl(); // Client 테이블 DAO
		String code = request.getParameter("code");
		IKakaoLoginService service = new KakaoLoginService();
		try {
			String role = "recruiter";
			User user = service.kakaoLogin(code, role);
			if(!clientDAO.existsClientById(user.getUserId())){
				// User 객체 생성
/*        user.setToken(null);
        user.setAcceptNoti(true);
        user.setAcceptConsent(true);*/
				user.setRegistrationDate(Date.valueOf(LocalDate.now()));
				user.setWithdrawalDate(null);

				// Client 객체 생성
				Client client = new Client();
				client.setClientId(user.getUserId());
				client.setCompanyRegNo(null);
				client.setCompanyName(null);
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
				}
			}else {
				HttpSession session = request.getSession();
				System.out.println("session : " + user.getUserId());
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("role", role); // 여기서 role 저장
				session.setAttribute("client_id", user.getUserId());
				response.sendRedirect(request.getContextPath() + "/mainPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
