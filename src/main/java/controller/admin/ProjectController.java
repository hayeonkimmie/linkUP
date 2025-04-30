/**
 * ProjectController.java
 * GET : 프로젝트 목록 조회 및 상세 조회
 * 1-1. Keyword Parameter를 통해 검색인지 아닌지 구분 있으면 검색 null이면 전체
 * 1-2. idParam Parameter를 통해 상세 조회인지 아닌지 구분 있으면 상세 null이면 전체
 * 2.
 */
package controller.admin;

import dao.admin.IProjectDAO;
import dao.admin.ProjectDAO;
import dto.AdminProject;
import dto.AdminProjectDetail;
import service.admin.IProjectService;
import service.admin.ProjectService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/project")
public class ProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProjectController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("now", new java.util.Date());
        String keyword = request.getParameter("keyword");
        keyword = (keyword != null && keyword.trim().isEmpty()) ? null : keyword;
        String settleStatus = request.getParameter("settleStatus");
        settleStatus = (settleStatus != null && settleStatus.trim().isEmpty()) ? null : settleStatus;
        String startDate = request.getParameter("startDate");
        startDate = (startDate != null && startDate.trim().isEmpty()) ? null : startDate;
        String endDate = request.getParameter("endDate");
        endDate = (endDate != null && endDate.trim().isEmpty()) ? null : endDate;
        ProjectDAO projectDAO = new ProjectDAO();
        IProjectService projectService = new ProjectService(projectDAO);
        String idParam = request.getParameter("id");
        String pageParam = request.getParameter("page");
        int curPage = (pageParam == null || pageParam.isEmpty()) ? 1 : Integer.parseInt(pageParam);
        int perPage = 8;
        int offset = (curPage - 1) * perPage;

        try {
            // 상세 페이지 : /admin/project_list.jsp
            // url: http://localhost:8085/linkup/admin/project?id=1
            if (idParam != null) {
                // ✅ 프로젝트 상세 페이지 처리
                AdminProjectDetail detail = projectService.selectProjectDetail(Integer.parseInt(idParam));
                request.setAttribute("project", detail);
                request.getRequestDispatcher("/admin/project_detail.jsp").forward(request, response);
            // 프로젝트 전체 목록 페이지 : /admin/project_list.jsp
            // url: http://localhost:8085/linkup/admin/project
            }else {
                List<AdminProject> projectList = projectService.getPagedProjects(offset, perPage, keyword, settleStatus, startDate, endDate);
                int totalCount = projectService.getTotalProjectCount(keyword, settleStatus, startDate, endDate);
                PageInfo pageInfo = projectService.calculatePageInfo(curPage, perPage, totalCount);

                request.setAttribute("totalCount", totalCount);
                request.setAttribute("projectList", projectList);
                request.setAttribute("pageInfo", pageInfo);

                request.getRequestDispatcher("/admin/project_list.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류 발생");
        }

    }
}
