package controller.home;

import service.IProjectService;
import service.ProjectService;
import dto.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/mainPage")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MainController 진입");

        // 세션에서 로그인 여부 확인
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        // 공통 데이터 세팅 (카테고리별 프로젝트)
        List<Project> devProjects = projectService.MainProjectsByCategory("웹 제작");
        List<Project> designProjects = projectService.MainProjectsByCategory("웹 유지보수");
        List<Project> planProjects = projectService.MainProjectsByCategory("프로그램");

        request.setAttribute("devProjects", devProjects);
        request.setAttribute("designProjects", designProjects);
        request.setAttribute("planProjects", planProjects);

        // 로그인 여부에 따라 JSP 분기
        String path = "/home/main.jsp";
        if (userId != null) {
            path = "/home/mainLoginVersion.jsp";
        }
        request.getSession().setAttribute("userId", "client001");
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
