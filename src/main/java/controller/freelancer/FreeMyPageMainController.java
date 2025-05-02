package controller.freelancer;

import dto.FreelancerProject;
import dto.JjimProj;
import service.freelancer.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page")
public class FreeMyPageMainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageMainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        IFreelancerProjectService fService = new FreelancerProjectService();
        IJjimProjService jService = new JjimProjService();

        try{
            Integer jjimProjCnt = jService.selectJjimProjCnt(freelancerId);
            System.out.println("jjimProjCnt: " + jjimProjCnt);
            if (jjimProjCnt > 0) {
                List<JjimProj> jjimProjList;
                jjimProjList = jService.selectJjimProjListForMain(freelancerId);
                System.out.println("JJimProjList 서블릿 47 JJimProjList = " + jjimProjList);
                request.setAttribute("jjimProjList", jjimProjList);
            } else if (jjimProjCnt == 0 || jjimProjCnt == null){
                request.setAttribute("jjimProjList", null);
            }
            Integer onGoingProjCnt = fService.cntOngoingProjects(freelancerId);
            if (onGoingProjCnt > 0) {
                List<FreelancerProject> onGoingProjList;
                onGoingProjList = fService.selectOngoingProjectForMain(freelancerId);
                System.out.println("OnGoingProjList 서블릿 47 OnGoingProjList = " + onGoingProjList);
                request.setAttribute("onGoingProjList", onGoingProjList);
            } else if (onGoingProjCnt == 0 || onGoingProjCnt == null){
                request.setAttribute("onGoingProjList", null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "찜한 프로젝트 조회에 실패했습니다.");
        }
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }
}