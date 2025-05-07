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
        System.out.println("subCategory: " + subCategory);
        List<Project> projectList;
        List<Freelancer> freelancerList;

        Map<String, String> param = new HashMap<>();

        if (category != null && !category.trim().isEmpty() && !"전체".equals(category)) {
            param.put("category", category);
        }

        if (subCategory != null && !subCategory.trim().isEmpty()) {
            param.put("subCategory", subCategory);
        }

        // 키워드가 있을 경우, 검색 필터로 처리
        if (keyword != null && !keyword.trim().isEmpty()) {
            param.put("keyword", keyword);
            projectList = projectService.searchProjectsByCategoryAndKeyword(param);
            freelancerList = freelancerService.searchFreelancersByCategoryAndKeyword(param);
        } else {
            projectList = projectService.catalogProjectByConditions(param);

            if (subCategory != null && !subCategory.trim().isEmpty()) {
                // 서브카테고리 이름을 ID로 변환 후 필터링
                try {
                    int subCategoryId = categoryService.findSubCategoryIdByName(subCategory);
                    freelancerList = freelancerService.catalogFreelancersBySubCategoryIds(List.of(subCategoryId));
                } catch (Exception e) {
                    freelancerList = List.of(); // 실패 시 빈 리스트
                    System.out.println("Invalid subCategory name or DB issue: " + subCategory);
                    e.printStackTrace();
                }

            } else if (category != null && !category.trim().isEmpty() && !"전체".equals(category)) {
                // 카테고리 선택 시 해당 카테고리의 모든 서브카테고리 ID로 필터링
                List<Integer> subCategoryIds = categoryService.findSubCategoryIdsByCategoryName(category);
                freelancerList = freelancerService.catalogFreelancersBySubCategoryIds(subCategoryIds);
            } else {
                // 전체 조회
                freelancerList = freelancerService.findAllFreelancers();
            }
        }

        for(Project project : projectList) {
            System.out.println("Project: " + project);
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
