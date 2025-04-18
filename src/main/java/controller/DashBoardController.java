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

        Integer totalPayment = 0;
        Integer totalProjectCount = 0;
        Integer completedProjectCount = 0;
        List<DashboardProject> ongoingProjects = new ArrayList<>();
        ProjectService projectService = new ProjectService();

        try{
            ongoingProjects = (projectService.getDashboardProjectList());
            for(DashboardProject dproject : ongoingProjects){
                totalPayment += dproject.getTotalPay();
                totalProjectCount++;
                if(dproject.getProjectStatus().equals("완료됨")){
                    completedProjectCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("totalPayment", totalPayment);
            request.setAttribute("totalProjectCount", totalProjectCount);
            request.setAttribute("completedProjectCount", completedProjectCount);
            request.setAttribute("ongoingProjects", ongoingProjects);
            request.getRequestDispatcher("/admin/admin_dashboard.jsp").forward(request, response);
        }

    }

}
