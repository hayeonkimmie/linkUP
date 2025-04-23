package controller.freelancer;

import dto.FreelancerProject;
import service.freelancer.FreelancerProjectService;
import service.freelancer.IFreelancerProjectService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page/project-status")
public class MyProjectStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyProjectStatus() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
            /*if (freelancerId == null) {
                response.sendRedirect("/login");
            };*/
        freelancerId = "free002";
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("36 page = " + page);
        System.out.println("project-status 서블릿 33 page = " + page);
        PageInfo pageInfo = new PageInfo(page);
        IFreelancerProjectService service = new FreelancerProjectService();
        List<FreelancerProject> onGoingProjectList = null;
        List<FreelancerProject> completedProjectList = null;
        Integer goingProjCnt = 0, completedProjCnt = 0;
        try {
            goingProjCnt = service.cntOngoingProjects(freelancerId);
            completedProjCnt = service.cntCompletedProjects(freelancerId);
            //진행중인 프로잭트
            request.setAttribute("goingProjCnt",goingProjCnt);
            request.setAttribute("completedProjCnt",completedProjCnt);
            if (goingProjCnt > 0) {
                onGoingProjectList = service.selectOngoingProject(pageInfo, freelancerId);
                System.out.println("MyProjectStatus 서블릿 53 projectList = " + goingProjCnt);
                request.setAttribute("onGoingProjectList", onGoingProjectList);
            } else if (goingProjCnt == 0) {
                request.setAttribute("onGoingProjectList", null);
            }

            //완료된 프로잭트
            if (completedProjCnt > 0) {
                completedProjectList = service.selectCompletedProject(pageInfo, freelancerId);
                System.out.println("MyProjectStatus 서블릿 59 projectList = " + completedProjectList);
                request.setAttribute("completedProjectList", completedProjectList);
            } else if (completedProjCnt == 0) {
                request.setAttribute("completedProjectList", null);
            }
            request.setAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/freelancer/my_project_status.jsp").forward(request, response);
    }
}