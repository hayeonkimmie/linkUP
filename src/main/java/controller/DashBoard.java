package controller;

import dto.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 관리자 대시보드로 이동하는 서블릿<br>
 * 보내는 데이터 : <br>
 *  이번달 총 거래액 : Integer <br>
 *  이번달 계약이 성사 된 프로젝트의 수 : Integer <br>
 *  이번달에 진행이 완료 된 프로젝트의 수 : Integer <br>
 *  이번달 진행중인 프로젝트의 정보 : List(Project) <br>
 */
@WebServlet("/admin/dashboard")
public class DashBoard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashBoard() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Integer totalPayment = 15689000;
        Integer totalProjectCount = 283;
        Integer completedProjectCount = 156;
        List<Project> ongoingProjects = new ArrayList<>();

        // 대시보드에 보여줄 데이터 지정하기
        ongoingProjects.add(new Project("Project A", "Description A", "2023-01-01", "2023-12-31", "Ongoing", "Manager A"));
        ongoingProjects.add(new Project("Project B", "Description B", "2023-02-01", "2023-11-30", "Ongoing", "Manager B"));
        ongoingProjects.add(new Project("Project C", "Description C", "2023-03-01", "2023-10-31", "Ongoing", "Manager C"));

        // 대시보드에 보여줄 데이터 request에 담기
        request.setAttribute("totalPayment", totalPayment);
        request.setAttribute("totalProjectCount", totalProjectCount);
        request.setAttribute("completedProjectCount", completedProjectCount);
        request.setAttribute("ongoingProjects", ongoingProjects);
        request.getRequestDispatcher("/admin/admin_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
