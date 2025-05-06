package controller.home;


import dao.common.IUserDAO;
import dao.common.UserDAO;
import dao.freelancer.FreelancerDAO;
import dto.Freelancer;
import dto.User;
import service.common.IKakaoLoginService;
import service.common.KakaoLoginService;
import service.home.CreateAccFreelancer;
import service.home.CreateAccRecruiter;
import service.home.ICreateAccFreelancer;
import service.home.ICreateAccRecruiter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Servlet implementation class KakaoLogin
 */
@WebServlet("/kakao-freelancer")
public class KakaoFreelancerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ICreateAccFreelancer createAccFreelancer = new CreateAccFreelancer();
	public KakaoFreelancerLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		FreelancerDAO freelancerDAO = new FreelancerDAO();
		String code = request.getParameter("code");
		IKakaoLoginService service = new KakaoLoginService();

		try {
			String role = "freelancer";
			User user = service.kakaoLogin(code, role);
			if(freelancerDAO.selectBasicFreelancerById(user.getUserId()) == null){
				user.setRegistrationDate(Date.valueOf(LocalDate.now()));
				user.setWithdrawalDate(null);
				Freelancer freelancer = new Freelancer();
				freelancer.setFreelancerId(user.getUserId());
				boolean isSuccess = createAccFreelancer.registerFreelancer(user, freelancer);

				if (isSuccess) {
					// 회원가입 성공 ➔ 메인 페이지로 이동
					response.sendRedirect(request.getContextPath() + "/mainPage");
				} else {
					response.sendRedirect(request.getContextPath() + "/error/error.jsp");
				}
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("role", "jobseeker"); // 여기서 role 저장
				response.sendRedirect(request.getContextPath() + "/mainPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
