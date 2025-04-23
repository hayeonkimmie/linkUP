package controller.home;

import service.IProjectService;
import service.ProjectService;
import dto.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/mainLoginVersion")
public class MainLoginVersionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IProjectService projectService;

    public MainLoginVersionController() {
        super();
        projectService = new ProjectService(); // 서비스 객체 생성
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MainLoginVersionController 들어옴!");

        // 카테고리별 프로젝트 리스트 가져오기
        List<Project> devProjects = projectService.MainProjectsByCategory("개발");
        List<Project> designProjects = projectService.MainProjectsByCategory("디자인");
        List<Project> planProjects = projectService.MainProjectsByCategory("기획");

        // 각각 따로 request에 담기
        request.setAttribute("devProjects", devProjects);
        request.setAttribute("designProjects", designProjects);
        request.setAttribute("planProjects", planProjects);

        request.getRequestDispatcher("./home/mainLoginVersion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
