package controller.home;

import dto.Freelancer;
import dto.Project;
import service.IProjectService;
import service.ProjectService;
import service.home.CatalogFreelancerServiceImpl;
import service.home.ICatalogFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/catalog")
public class CatalogController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProjectService projectService = new ProjectService();
    private final ICatalogFreelancerService freelancerService = new CatalogFreelancerServiceImpl();

    public CatalogController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String keyword = request.getParameter("keyword");

        List<Project> projectList;
        List<Freelancer> freelancerList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // ✅ Map 안전하게 생성 (null 허용X)
            Map<String, String> param = new HashMap<>();
            param.put("category", category);
            param.put("keyword", keyword);

            // ✅ 검색 결과 가져오기
            projectList = projectService.searchProjectsByCategoryAndKeyword(param);
            freelancerList = freelancerService.searchFreelancersByCategoryAndKeyword(param);
        } else {
            // ✅ 검색어 없을 경우 전체 조회
            projectList = projectService.catalogProjectByCategory(category);
            freelancerList = freelancerService.catalogFreelancersByCategory(category);
        }

        // ✅ JSP 전달
        request.setAttribute("category", category);
        request.setAttribute("keyword", keyword); // 검색어 유지
        request.setAttribute("projectList", projectList);
        request.setAttribute("freelancerList", freelancerList);

        request.getRequestDispatcher("./home/catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용 안 함
    }
}
