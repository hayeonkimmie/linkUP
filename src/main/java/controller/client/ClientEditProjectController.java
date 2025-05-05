package controller.client;

import com.google.gson.Gson;
import dto.Category;
import dto.Pay;
import dto.Project;
import dto.ProjectMgt;
import service.client.ClientEditProjectServiceImpl;
import service.client.IClientEditProjectService;
import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;
import service.common.CategoryMenuServiceImpl;
import service.common.ICategoryMenuService;
import service.home.IPayService;
import service.home.PayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/editProject")
public class ClientEditProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private final IClientEditProjectService editService = new ClientEditProjectServiceImpl();
    private final IPayService payService = new PayService(); // Pay 서비스 추가
    private final ICategoryMenuService categoryService = new CategoryMenuServiceImpl(); // 카테고리 서비스 추가

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 캐시 방지 헤더 추가
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        String projectIdParam = request.getParameter("projectId");

        if (projectIdParam == null || projectIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "projectId 파라미터가 없습니다.");
            return;
        }

        try {
            int projectId = Integer.parseInt(projectIdParam);

            // 프로젝트 정보 가져오기
            Project project = editService.getProjectById(projectId);

            // 포지션(Pay) 정보 가져오기
            List<Pay> positions = payService.getPaysByProjectId(projectId);

            // 카테고리 정보 가져오기
            List<Category> categoryList = categoryService.getAllCategoriesWithSub();
            String categoryListJSON = new Gson().toJson(categoryList);

            // 요청 속성 설정
            request.setAttribute("project", project);
            request.setAttribute("positions", positions);
            request.setAttribute("categoryListJSON", categoryListJSON);
            request.setAttribute("mode", "edit");

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
        // 캐시 방지 헤더 추가
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");


        String projectIdParam = request.getParameter("projectId");
        if (projectIdParam == null || projectIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "projectId가 없습니다.");
            return;
        }

        try {
            int projectId = Integer.parseInt(projectIdParam);

            // 프로젝트 정보 수정
            Project project = new Project();
            project.setProjectId(projectId);
            project.setAdvertisementTitle(request.getParameter("advertisementTitle"));
            project.setProjectName(request.getParameter("projectName"));

            // 모집분야 처리
            String[] jobPositions = request.getParameterValues("jobPosition");
            if (jobPositions != null && jobPositions.length > 0) {
                project.setJobPosition(String.join(",", jobPositions));
            } else {
                project.setJobPosition("");
            }

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

            // 프로젝트 정보 업데이트
            editService.updateProject(project);

            // 포지션 정보 업데이트 - 기존 포지션 전부 삭제 후 새로 추가
            payService.deletePaysByProjectId(projectId);

            // 새 포지션 정보 추가
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
                    pay.setProjectId(projectId);
                    pay.setLvId(safeParseInt(lvIds[i], 0));
                    pay.setSubCategoryId(project.getSubCategoryId());
                    pay.setWork(works[i]);
                    pay.setPeople(safeParseInt(peoples[i], 0));
                    pay.setProjectFee(safeParseInt(projectFees[i], 0));

                    payService.registerPay(pay);
                }
            }
            IProjectMgtService service = new ProjectMgtServiceImpl();
            Map<String, Object> param = new HashMap<>();
            String clientId = (String) request.getSession().getAttribute("userId");
            if (clientId == null || clientId.isEmpty()) {
                clientId = "client001"; // 테스트용 기본값
            }
            // 파라미터로 status받기 (전체보기, 구인중, 시작전, 진행중, 종료됨)
            String status = request.getParameter("status");
            if (status == null || status.isEmpty()) {
                status = "all"; // 기본값: 전체보기
            }
            param.put("clientId", clientId);
            param.put("status", status);
            List<ProjectMgt> projectList = service.getProjectByStatus(param);

            // 수정 완료 후 마이페이지로 리다이렉트
            request.setAttribute("projectList", projectList);
            request.setAttribute("status", status); // 상태 값 받아오기
            response.sendRedirect(request.getContextPath()+"/clientRecruitMgt");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "프로젝트 수정 중 오류 발생");
        }
    }

    // 안전한 정수 변환
    private int safeParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // 빈 문자열 체크
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}