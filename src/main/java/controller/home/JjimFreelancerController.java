package controller.home;

import service.IProjectService;
import service.ProjectService;
import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;
import service.freelancer.FreelancerService;
import service.freelancer.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/JJimFree")
public class JjimFreelancerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JjimFreelancerController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 로그인 정보자 사용자 상태 저장
        String clientId = (String) session.getAttribute("userId"); // 세션에 저장된 로그인 유저ID
        // String freelancerId = (String) request.getSession().getAttribute("userId");
        String freelancerId = request.getParameter("freelancerid");
        String action = request.getParameter("action");
        boolean result = false;
        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        try {
            if(action.equals("cancel")) {
                service.cancelLikeFreelancer(freelancerId, clientId);
            } else {
                service.likeFreelancer(freelancerId, clientId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       /* if (result) { // 성공
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('문의 등록이 완료되었습니다.'); location.href='" + request.getContextPath() + "/freelancer?freelancerid="+freelancerId+"';</script>");
        } else { // 실패
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('문의 등록을 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
        }*/
    }
}