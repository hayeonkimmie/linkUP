/**
 * ClientListController.java
 * GET : 업체(구인자)가 진행한 프로젝트 목록을 렌더링
 * POST : //
 */
package controller.admin;

import dto.AdminProject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/client-list")
public class ClientListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String comapnyid = request.getParameter("companyid");
        List<AdminProject> ongoingProjectList = new ArrayList<>();

        request.setAttribute("ongoingProjectList", ongoingProjectList);
        request.getRequestDispatcher("/admin/client_project_list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
