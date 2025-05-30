package controller.freelancer;

import com.google.gson.Gson;
import dto.FreelancerProject;
import dto.SettlementListForF;
import service.freelancer.FreelancerProjectService;
import service.freelancer.FreelancerSettlementService;
import service.freelancer.IFreelancerProjectService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/my-page/project-status")
public class MyProjectStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyProjectStatus() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String pageStr = request.getParameter("page");
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("page = " + page);
        PageInfo pageInfo = new PageInfo(page);
        System.out.println("pageInfo = " + pageInfo);
        IFreelancerProjectService projectService = new FreelancerProjectService();
        FreelancerSettlementService settlementService = new FreelancerSettlementService();

        try {
            int goingProjCnt = projectService.cntOngoingProjects(freelancerId);
            int completedProjCnt = projectService.cntCompletedProjects(freelancerId);

            List<FreelancerProject> onGoingProjectList = (goingProjCnt > 0)
                    ? projectService.selectOngoingProject(pageInfo, freelancerId)
                    : null;

            List<FreelancerProject> completedProjectList = (completedProjCnt > 0)
                    ? projectService.selectCompletedProject(pageInfo, freelancerId)
                    : null;

            // 정산 리스트 맵 생성
            Map<Integer, List<SettlementListForF>> onGoingProjSettlementMap = new HashMap<>();
            if (onGoingProjectList != null) {
                for (FreelancerProject project : onGoingProjectList) {
                    System.out.println(project.getProjectId() +" "+ project.getProjectName());
                    List<SettlementListForF> settlementList = settlementService.getSettlementList(freelancerId, project.getProjectId());
                    if(settlementList == null || settlementList.isEmpty()){
                        settlementList = new ArrayList<>();
                        settlementList.add(new SettlementListForF(project.getProjectId(), project.getProjectName()));
                        System.out.println("리스트 크기: " + settlementList.size());
                        System.out.println("첫 번째 항목: " + settlementList.get(0));
                    }
                    onGoingProjSettlementMap.put(project.getProjectId(), settlementList);
                }
            }

            Map<Integer, List<SettlementListForF>> completedProjSettlementMap = new HashMap<>();
            if (completedProjectList != null) {
                for (FreelancerProject project : completedProjectList) {
                    List<SettlementListForF> settlementList = settlementService.getSettlementList(freelancerId, project.getProjectId());
                    if(settlementList == null || settlementList.isEmpty()){
                        settlementList = new ArrayList<>();
                        settlementList.add(new SettlementListForF(project.getProjectId(), project.getProjectName()));
                    }
                    System.out.println(settlementList);
                    completedProjSettlementMap.put(project.getProjectId(), settlementList);
                }
            }
            // JSON 변환
            Gson gson = new Gson();
            String onGoingProjSettlementMapJson = gson.toJson(onGoingProjSettlementMap);
            String completedProjSettlementMapJson = gson.toJson(completedProjSettlementMap);
            System.out.println("onGoingProjSettlementMap"+onGoingProjSettlementMap);
            System.out.println("completedProjSettlementMap"+completedProjSettlementMap);
            System.out.println("onGoingProjectList"+onGoingProjectList);
            System.out.println("completedProjectList"+completedProjectList);

            // Attribute 설정
            request.setAttribute("goingProjCnt", goingProjCnt);
            request.setAttribute("completedProjCnt", completedProjCnt);
            request.setAttribute("onGoingProjectList", onGoingProjectList);
            request.setAttribute("completedProjectList", completedProjectList);
            request.setAttribute("onGoingProjSettlementMapJson", onGoingProjSettlementMapJson);
            request.setAttribute("completedProjSettlementMapJson", completedProjSettlementMapJson);
            request.setAttribute("pageInfo", pageInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/freelancer/my_project_status.jsp").forward(request, response);
    }
}
