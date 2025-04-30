package controller.home;

import dto.Category;
import dto.Pay;
import dto.Project;
import service.home.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/makeProject")
public class MakeProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakeProjectController() {
        super();
    }
    private final IProjectService projectService = new ProjectService();
    private final IPayService payService = new PayService();
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Category> categoryList = categoryService.getAllCategoriesWithSub();
//        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("./home/makeProject.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 2. 프로젝트 정보 받기
        Project project = new Project();
        project.setClientId(request.getParameter("clientId")); // 세션이나 폼에서 가져오기
        project.setAdvertisementTitle(request.getParameter("advertisementTitle"));
        project.setProjectName(request.getParameter("projectName"));
        project.setJobPosition(request.getParameter("jobPosition"));
        project.setWorkingMethod(request.getParameter("workingMethod"));
        project.setWorkingHours(request.getParameter("workingHours"));
        project.setDuration(Integer.parseInt(request.getParameter("duration")));
        project.setWorkingEnvironment(request.getParameter("workingEnvironment"));
        project.setReqSkills(request.getParameter("reqSkills"));
        project.setWantedSkills(request.getParameter("wantedSkills"));
        project.setProjectDescription(request.getParameter("projectDescription"));
        project.setJobDetails(request.getParameter("jobDetails"));
        project.setQualification(request.getParameter("qualification"));
        project.setPreferentialConditions(request.getParameter("preferentialConditions"));
        project.setDeadlineDate(java.sql.Date.valueOf(request.getParameter("deadlineDate")));
        project.setManager(request.getParameter("manager"));
        project.setMphone(request.getParameter("mphone"));
        project.setMemail(request.getParameter("memail"));
        project.setSubCategoryId(Integer.parseInt(request.getParameter("subCategoryId")));

        // 3. 프로젝트 먼저 등록
        projectService.registerProject(project);

        // ★★★ 주의 ★★★
        // project_id (PK) 받아서 pay 등록할 때 넣어야 하는데,
        // 만약 project_id 자동증가를 받아야 한다면 약간 로직 추가 필요함 (뒤에서 다시 설명)

        // 4. 포지션 정보 받기
        String[] lvIds = request.getParameterValues("lvId");
        String[] works = request.getParameterValues("work");
        String[] peoples = request.getParameterValues("people");
        String[] projectFees = request.getParameterValues("projectFee");

        if (lvIds != null && works != null && peoples != null && projectFees != null) {
            for (int i = 0; i < lvIds.length; i++) {
                Pay pay = new Pay();
                pay.setProjectId(project.getProjectId()); // 등록한 프로젝트 id 넣기
                pay.setLvId(Integer.parseInt(lvIds[i]));
                pay.setSubCategoryId(project.getSubCategoryId()); // 서브카테고리 id는 Project에서 그대로
                pay.setWork(works[i]);
                pay.setPeople(Integer.parseInt(peoples[i]));
                pay.setProjectFee(Integer.parseInt(projectFees[i]));

                payService.registerPay(pay);
            }
        }

        // 5. 등록 완료 후 이동
        response.sendRedirect(request.getContextPath() + "/mainPage");
    }
}
