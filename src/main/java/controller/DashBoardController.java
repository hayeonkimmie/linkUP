package controller;

import dto.DashboardProject;
import service.ProjectService;

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
public class DashBoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DashBoardController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Integer totalPayment = 15689000;
        Integer totalProjectCount = 283;
        Integer completedProjectCount = 156;
        List<DashboardProject> ongoingProjects = new ArrayList<>();

        ProjectService projectService = new ProjectService();

        // 대시보드에 보여줄 데이터 지정하기
        ongoingProjects = (projectService.getDashboardProjectList());

//        ongoingProjects.add(new Project(1, "테스트 상품 스펜 체인 풀 슬리드 티켓스", "2023-03-11", 1000000, "진행중", "김매니저", "대기중"));
//        ongoingProjects.add(new Project(2, "테스트 플랫폼 커뮤니티 개설", "2023-03-11", 1000000, "진행중", "이매니저","대기중"));
//        ongoingProjects.add(new Project(3, "테스트 상품3", "2023-02-11", 1000000, "진행중", "박매니저","대기중"));

        // 대시보드에 보여줄 데이터 request에 담기
        request.setAttribute("totalPayment", totalPayment);
        request.setAttribute("totalProjectCount", totalProjectCount);
        request.setAttribute("completedProjectCount", completedProjectCount);
        request.setAttribute("ongoingProjects", ongoingProjects);
        request.getRequestDispatcher("/admin/admin_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }
}
