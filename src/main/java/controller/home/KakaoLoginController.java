package controller.home;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.client.ClientDAOImpl;
import dao.common.UserDAO;
import dao.freelancer.FreelancerDAO;
import dto.User;
import service.common.KakaoLoginService;
import service.common.IKakaoLoginService;

/**
 * Servlet implementation class KakaoLogin
 */
@WebServlet("/kakao")
public class KakaoLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KakaoLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDAO userDAO = new UserDAO();          // User 테이블 DAO
		ClientDAOImpl clientDAO = new ClientDAOImpl(); // Client 테이블 DAO
		FreelancerDAO freelancerDAO = new FreelancerDAO();
		String code = request.getParameter("code");
		IKakaoLoginService service = new KakaoLoginService();
		String role = request.getParameter("role");

		try {
			User user = service.kakaoLogin(code);
			if(userDAO.selectUserById(user.getUserId()) == null){
				request.getRequestDispatcher("./home/createAccEmail.jsp").forward(request,response);
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("role", role); // 여기서 role 저장
				if ("recruiter".equals(role)) {
					session.setAttribute("client_id", user.getUserId());
				}
				response.sendRedirect(request.getContextPath() + "/mainPage");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
