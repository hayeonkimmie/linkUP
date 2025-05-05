package controller.home;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String code = request.getParameter("code");
		System.out.println("kakao 29 "+code);
		User user = new User();
		IKakaoLoginService service = new KakaoLoginService();
		try {
			service.kakaoLogin(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
