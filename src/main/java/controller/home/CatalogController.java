package controller.home;

import dto.Freelancer;
import dto.Project;
import service.IProjectService;
import service.ProjectService;
import service.home.CatalogFreelancerServiceImpl;
import service.home.CategoryServiceImpl;
import service.home.ICatalogFreelancerService;
import service.home.ICategoryService;

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
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String subCategory = request.getParameter("subCategory");
        String keyword = request.getParameter("keyword");

        List<Project> projectList;
        List<Freelancer> freelancerList;

        Map<String, String> param = new HashMap<>();

        if (category != null && !category.trim().isEmpty() && !"전체".equals(category)) {
            param.put("category", category);
        }

        if (subCategory != null && !subCategory.trim().isEmpty()) {
            param.put("subCategory", subCategory);
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            param.put("keyword", keyword);
            projectList = projectService.searchProjectsByCategoryAndKeyword(param);
            freelancerList = freelancerService.searchFreelancersByCategoryAndKeyword(param);
        } else {
            projectList = projectService.catalogProjectByConditions(param);

            // ✅ 카테고리로 하위 sub_category_id 리스트를 조회해 프리랜서 필터링
            if (category != null && !category.trim().isEmpty() && !"전체".equals(category)) {
                List<Integer> subCategoryIds = categoryService.findSubCategoryIdsByCategoryName(category);
                freelancerList = freelancerService.catalogFreelancersBySubCategoryIds(subCategoryIds);
            } else {
                // ✅ 카테고리 '전체'일 경우: 모든 프리랜서 조회
                freelancerList = freelancerService.findAllFreelancers();
            }
        }

        request.setAttribute("category", category);
        request.setAttribute("subCategory", subCategory);
        request.setAttribute("keyword", keyword);
        request.setAttribute("projectList", projectList);
        request.setAttribute("freelancerList", freelancerList);

        request.getRequestDispatcher("./home/catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST는 사용하지 않음
    }
}
