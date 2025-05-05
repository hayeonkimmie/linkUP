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

        if(clientId==null || clientId.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('로그인 후 다시 시도해주세요.'); history.back();</script>");
            return;
        }
        String freelancerId = request.getParameter("freelancerId");
        System.out.println("freelancerId " + freelancerId);
        String action = request.getParameter("action");
        if(action==null || action.isEmpty()) {
            action = "";
        }
        System.out.println(action);
        boolean result = false;
        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        try {
            if(!(action.equals("cancel"))) {
                service.likeFreelancer(freelancerId, clientId);
            } else {
                service.cancelLikeFreelancer(freelancerId, clientId);
            }
            result = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       if (result) { // 성공
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>location.href='" + request.getContextPath() + "/freelancer?freelancerid="+freelancerId+"';</script>");
        } else { // 실패
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>history.back();</script>");
        }
    }
}