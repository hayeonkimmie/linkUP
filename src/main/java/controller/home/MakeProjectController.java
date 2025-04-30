package controller.home;

import com.google.gson.Gson;
import dto.Category;
import dto.Pay;
import dto.Project;
import service.common.CategoryMenuServiceImpl;
import service.common.ICategoryMenuService;
import service.home.IPayService;
import service.home.IProjectService;
import service.home.PayService;
import service.home.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/makeProject")
public class MakeProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProjectService projectService = new ProjectService();
    private final IPayService payService = new PayService();
    private final ICategoryMenuService categoryService = new CategoryMenuServiceImpl();

    public MakeProjectController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategoriesWithSub();
        String categoryListJSON = new Gson().toJson(categoryList);
        request.setAttribute("categoryListJSON", categoryListJSON);
        request.getRequestDispatcher("/home/makeProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String clientId = (String) session.getAttribute("client_id");

        if (!"recruiter".equals(role) || clientId == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "사업자만 프로젝트를 등록할 수 있습니다.");
            return;
        }

        Project project = new Project();
        project.setClientId(clientId);
        project.setAdvertisementTitle(request.getParameter("advertisementTitle"));
        project.setProjectName(request.getParameter("projectName"));
        String[] jobPositions = request.getParameterValues("jobPosition");
        if (jobPositions != null && jobPositions.length > 0) {
            project.setJobPosition(String.join(",", jobPositions));
        } else {
            project.setJobPosition("");
        }
        project.setWorkingMethod(request.getParameter("workingMethod"));
        project.setWorkingHours(request.getParameter("workingHours"));
        project.setWorkingEnvironment(request.getParameter("workingEnvironment"));
        project.setReqSkills(request.getParameter("reqSkills"));
        project.setWantedSkills(request.getParameter("wantedSkills"));
        project.setProjectDescription(request.getParameter("projectDescription"));
        project.setJobDetails(request.getParameter("jobDetails"));
        project.setQualification(request.getParameter("qualification"));
        project.setPreferentialConditions(request.getParameter("preferentialConditions"));
        project.setManager(request.getParameter("manager"));
        project.setMphone(request.getParameter("mphone"));
        project.setMemail(request.getParameter("memail"));

        // 안전한 정수 변환 처리
        project.setDuration(safeParseInt(request.getParameter("duration"), 0));

        String subCategoryIdStr = request.getParameter("subCategoryId");
        if (subCategoryIdStr == null || subCategoryIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "하위 카테고리를 선택해주세요.");
            return;
        }
        project.setSubCategoryId(Integer.parseInt(subCategoryIdStr));

        try {
            project.setDeadlineDate(Date.valueOf(request.getParameter("deadlineDate")));
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "마감일이 올바르지 않습니다.");
            return;
        }

        // 프로젝트 저장
        projectService.registerProject(project);

        // 포지션 처리
        String[] lvIds = request.getParameterValues("lvId");
        String[] works = request.getParameterValues("work");
        String[] peoples = request.getParameterValues("people");
        String[] projectFees = request.getParameterValues("projectFee");

        if (lvIds != null && works != null && peoples != null && projectFees != null) {
            for (int i = 0; i < lvIds.length; i++) {
                if (isEmpty(lvIds[i]) || isEmpty(works[i]) || isEmpty(peoples[i]) || isEmpty(projectFees[i])) {
                    continue; // 필드가 비었으면 건너뜀
                }

                Pay pay = new Pay();
                pay.setProjectId(project.getProjectId());
                pay.setLvId(safeParseInt(lvIds[i], 0));
                pay.setSubCategoryId(project.getSubCategoryId());
                pay.setWork(works[i]);
                pay.setPeople(safeParseInt(peoples[i], 0));
                pay.setProjectFee(safeParseInt(projectFees[i], 0));

                payService.registerPay(pay);
            }
        }

        response.sendRedirect(request.getContextPath() + "/mainPage");
    }

    private int safeParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
