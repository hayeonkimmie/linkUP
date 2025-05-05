package controller.home;

import dto.Pay;
import dto.Project;
import dto.ProjectDetail;
import service.IProjectService;
import service.ProjectService;
import service.home.IPayService;
import service.home.PayService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/project")
public class ProjectDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProjectDetailController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        request.getSession().setAttribute("userId", "free002");
        String projectId = request.getParameter("projectid");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        ProjectDetail selectedProject = null;
        List<Pay> projectPayList = null;
        IProjectService projectService = new ProjectService();
        IPayService payService = new PayService();
        boolean isLiked = false;
        try {
            selectedProject = projectService.selectProjectById(Integer.parseInt(projectId));
            projectPayList = payService.selectPayByProjectId(Integer.parseInt(projectId));
            if(freelancerId != null || !freelancerId.isEmpty()) {
                isLiked = projectService.isProjectLiked(freelancerId, Integer.parseInt(projectId));
            } else {
                isLiked = false;
            }
            System.out.println("42 "+isLiked );
            System.out.println(selectedProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("projectPayList", projectPayList);
        request.setAttribute("userId", request.getSession().getAttribute("userId"));
        request.setAttribute("project", selectedProject);
        request.setAttribute("projectId", projectId);
        request.setAttribute("isLiked", isLiked);
        request.getRequestDispatcher("/home/project_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
