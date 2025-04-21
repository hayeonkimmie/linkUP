package controller.admin;

import dao.admin.ProjectDAO;
import dto.AdminProject;
import dto.AdminProjectDetail;
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

        ProjectDAO projectDAO = new ProjectDAO();
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
                int id = Integer.parseInt(idParam);
                AdminProjectDetail detail = projectDAO.selectProjectDetail(id);
                request.setAttribute("project", detail);
                request.getRequestDispatcher("/admin/project_detail.jsp").forward(request, response);
            // 프로젝트 전체 목록 페이지 : /admin/project_list.jsp
            // url: http://localhost:8085/linkup/admin/project
            } else {
                List<AdminProject> projectList = projectDAO.selectPagedProjects(offset, perPage);
                int totalCount = projectDAO.countAllProjects();
                PageInfo pageInfo = new PageInfo(curPage);
                int allPage = (int) Math.ceil((double) totalCount / perPage);
                pageInfo.setAllPage(allPage);

                int startPage = Math.max(1, curPage - 2);
                int endPage = Math.min(allPage, startPage + 4);
                pageInfo.setStartPage(startPage);
                pageInfo.setEndPage(endPage);
                request.setAttribute("projectList", projectList);
                request.setAttribute("pageInfo", pageInfo);
                request.getRequestDispatcher("/admin/project_list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류 발생");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
