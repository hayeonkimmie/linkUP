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
        String subCategory = request.getParameter("subCategory");
        String keyword = request.getParameter("keyword");

        List<Project> projectList;
        List<Freelancer> freelancerList;

        Map<String, String> param = new HashMap<>();

        // ✅ 카테고리가 유효할 때만 Map에 추가
        if (category != null && !category.trim().isEmpty() && !"전체".equals(category)) {
            param.put("category", category);
        }

        if (subCategory != null && !subCategory.trim().isEmpty()) {
            param.put("subCategory", subCategory);
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            param.put("keyword", keyword);

            // ✅ keyword 있는 경우: 검색
            projectList = projectService.searchProjectsByCategoryAndKeyword(param);
            freelancerList = freelancerService.searchFreelancersByCategoryAndKeyword(param);
        } else {
            // ✅ keyword 없는 경우: 카테고리 기반 필터
            projectList = projectService.catalogProjectByConditions(param);
            freelancerList = freelancerService.catalogFreelancersByCategory(category);
        }

        // ✅ JSP로 전달
        request.setAttribute("category", category);
        request.setAttribute("subCategory", subCategory);
        request.setAttribute("keyword", keyword);
        request.setAttribute("projectList", projectList);
        request.setAttribute("freelancerList", freelancerList);

        request.getRequestDispatcher("./home/catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용 안 함
    }
}
