package controller.project;

import dto.Project;
import dto.ProjectDetail;
import service.client.ClientEditProjectServiceImpl;
import service.client.IClientEditProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/editProject")
public class ClientEditProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IClientEditProjectService editService = new ClientEditProjectServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectIdParam = request.getParameter("projectId");

        if (projectIdParam == null || projectIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "projectId 파라미터가 없습니다.");
            return;
        }

        try {
            int projectId = Integer.parseInt(projectIdParam);

            Project project = editService.getProjectById(projectId);
            request.setAttribute("project", project);
            request.setAttribute("mode", "edit");  // JSP에서 수정모드 인식
            request.getRequestDispatcher("./client/editProject.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "projectId는 숫자여야 합니다.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "프로젝트 데이터를 불러오는 데 실패했습니다.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String projectIdParam = request.getParameter("projectId");
        if (projectIdParam == null || projectIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "projectId가 없습니다.");
            return;
        }

        try {
            int projectId = Integer.parseInt(projectIdParam);

            Project project = new Project();
            project.setProjectId(projectId);
            project.setAdvertisementTitle(request.getParameter("advertisementTitle"));
            project.setProjectName(request.getParameter("projectName"));
            project.setJobPosition(request.getParameter("jobPosition"));
            project.setWorkingMethod(request.getParameter("workingMethod"));
            project.setWorkingHours(request.getParameter("workingHours"));
            project.setDuration(safeParseInt(request.getParameter("duration"), 0));
            project.setWorkingEnvironment(request.getParameter("workingEnvironment"));
            project.setReqSkills(request.getParameter("reqSkills"));
            project.setWantedSkills(request.getParameter("wantedSkills"));
            project.setProjectDescription(request.getParameter("projectDescription"));
            project.setJobDetails(request.getParameter("jobDetails"));
            project.setQualification(request.getParameter("qualification"));
            project.setPreferentialConditions(request.getParameter("preferentialConditions"));

            try {
                project.setDeadlineDate(Date.valueOf(request.getParameter("deadlineDate")));
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "날짜 형식이 잘못되었습니다.");
                return;
            }

            project.setManager(request.getParameter("manager"));
            project.setMphone(request.getParameter("mphone"));
            project.setMemail(request.getParameter("memail"));
            project.setSubCategoryId(safeParseInt(request.getParameter("subCategoryId"), 0));

            editService.updateProject(project);

            // 수정 완료 후 마이페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/clientRecruitMgt");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "프로젝트 수정 중 오류 발생");
        }
    }

    // 🔒 안전한 정수 변환
    private int safeParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
