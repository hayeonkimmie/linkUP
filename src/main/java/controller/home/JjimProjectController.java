package controller.home;

import service.IProjectService;
import service.ProjectService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/JJimProject")
public class JjimProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JjimProjectController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 로그인 정보자 사용자 상태 저장
        //String clientId = (String) session.getAttribute("userId"); // 세션에 저장된 로그인 유저ID
        String userId = (String) session.getAttribute("userId");
        System.out.println("action parameter = " +  request.getParameter("action"));
        System.out.println("projectId parameter = " +  request.getParameter("projectId"));
        System.out.println("userId parameter = " + userId);

        if(userId==null || userId.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('로그인 후 다시 시도해주세요.'); history.back();</script>");
            return;
        }
        String projectId = request.getParameter("projectId");
        String action = request.getParameter("action");
        if(action==null || action.isEmpty()) {
            action = "like";
        }
        boolean result = false;
        IProjectService service = new ProjectService();
        System.out.println("projectId " + projectId);
        System.out.println("userId " + userId);
        try {
            if(userId == null) {
                // 세션에서 다시 가져오기 시도
                userId = (String) session.getAttribute("userId");
                System.out.println("Retrieved userId again: " + userId);
            }
            if(!(action.equals("cancel"))) {
               int cnt = service.likeProject(userId, Integer.parseInt(projectId));
                result = cnt==1;
            } else {
                service.cancelProjectlike(userId, Integer.parseInt(projectId));
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result) { // 성공
            response.setContentType("text/html; charset=UTF-8");
            if(action.equals("cancel")){
                response.getWriter().println("<script>location.href='" + request.getContextPath() + "/project?projectid="+projectId+"';</script>");
            } else {
                response.getWriter().println("<script>location.href='" + request.getContextPath() + "/project?projectid="+projectId+"';</script>");
            }
        } else { // 실패
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('다시 시도해주세요.'); history.back();</script>");
        }
    }
}