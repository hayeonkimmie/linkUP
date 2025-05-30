package controller.home;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
        String uploadPath = request.getServletContext().getRealPath("/upload");
        int sizeLimit = 10 * 1024 * 1024; // 10MB
        MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

        if (!"recruiter".equals(role) || clientId == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "사업자만 프로젝트를 등록할 수 있습니다.");
            return;
        }

        Project project = new Project();
        project.setClientId(clientId);

//        project.setAdvertisementTitle(request.getParameter("advertisementTitle"));
//        project.setProjectName(request.getParameter("projectName"));
//        String[] jobPositions = request.getParameterValues("jobPosition");
//        if (jobPositions != null && jobPositions.length > 0) {
//            project.setJobPosition(String.join(",", jobPositions));
//        } else {
//            project.setJobPosition("");
//        }
//        project.setWorkingMethod(request.getParameter("workingMethod"));
//        project.setWorkingHours(request.getParameter("workingHours"));
//        project.setWorkingEnvironment(request.getParameter("workingEnvironment"));
//        project.setReqSkills(request.getParameter("reqSkills"));
//        project.setWantedSkills(request.getParameter("wantedSkills"));
//        project.setProjectDescription(request.getParameter("projectDescription"));
//        project.setJobDetails(request.getParameter("jobDetails"));
//        project.setQualification(request.getParameter("qualification"));
//        project.setPreferentialConditions(request.getParameter("preferentialConditions"));
//        project.setManager(request.getParameter("manager"));
//        project.setMphone(request.getParameter("mphone"));
//        project.setMemail(request.getParameter("memail"));

        project.setAdvertisementTitle(multi.getParameter("advertisementTitle"));
        project.setProjectName(multi.getParameter("projectName"));
        String[] jobPositions = multi.getParameterValues("jobPosition");
        if (jobPositions != null && jobPositions.length > 0) {
            project.setJobPosition(String.join(",", jobPositions));
        } else {
            project.setJobPosition("");
        }
        project.setWorkingMethod(multi.getParameter("workingMethod"));
        project.setWorkingHours(multi.getParameter("workingHours"));
        project.setWorkingEnvironment(multi.getParameter("workingEnvironment"));
        project.setReqSkills(multi.getParameter("reqSkills"));
        project.setWantedSkills(multi.getParameter("wantedSkills"));
        project.setProjectDescription(multi.getParameter("projectDescription"));
        project.setJobDetails(multi.getParameter("jobDetails"));
        project.setQualification(multi.getParameter("qualification"));
        project.setPreferentialConditions(multi.getParameter("preferentialConditions"));
        project.setManager(multi.getParameter("manager"));
        project.setMphone(multi.getParameter("mphone"));
        project.setMemail(multi.getParameter("memail"));

        // 썸네일 처리
        String thumbnailFileName = multi.getFilesystemName("thumbnail");
        project.setThumbnail(thumbnailFileName); // 테이블 컬럼과 DTO 필드 일치 확인 필요

        // 안전한 정수 변환 처리
        // ✅ 변경된 부분: 시작일, 종료일 처리 및 duration 자동 계산
        try {
            Date startDate = Date.valueOf(multi.getParameter("startDate"));
            Date endDate = Date.valueOf(multi.getParameter("endDate"));
            project.setStartDate(startDate);
            project.setEndDate(endDate);

            // duration 계산
            long duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
            if (duration < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "종료일은 시작일 이후여야 합니다.");
                return;
            }
            project.setDuration((int) duration);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "시작일과 종료일을 올바르게 입력해주세요.");
            return;
        }


        String subCategoryIdStr = multi.getParameter("subCategoryId");
        if (subCategoryIdStr == null || subCategoryIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "하위 카테고리를 선택해주세요.");
            return;
        }
        project.setSubCategoryId(Integer.parseInt(subCategoryIdStr));

        try {
            project.setDeadlineDate(Date.valueOf(multi.getParameter("deadlineDate")));
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "마감일이 올바르지 않습니다.");
            return;
        }

        // 프로젝트 저장
        System.out.println("등록할 프로젝트 정보 : " + project);
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
