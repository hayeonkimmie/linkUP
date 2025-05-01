package controller.home;

import dto.ProjectDetail;
import service.IProjectService;
import service.ProjectService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/project")
public class ProjectDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProjectDetailController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String projectId = request.getParameter("projectid");
        ProjectDetail selectedProject = null;
        IProjectService projectService = new ProjectService();
        try {
            selectedProject = projectService.selectProjectById(Integer.parseInt(projectId));
//            selectedProject = projectService.selectProjectById(39);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("project", selectedProject);
        request.getRequestDispatcher("/home/project_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
