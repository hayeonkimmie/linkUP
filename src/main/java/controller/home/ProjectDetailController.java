package controller.home;

import dao.home.ILevelDAO;
import dao.home.LevelDAOImpl;
import dto.*;
import service.IProjectService;
import service.ProjectService;
import service.home.IPayService;
import service.home.PayService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/project")
public class ProjectDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProjectDetailController() {
        super();
    }

    // 프로젝트 컨트롤러 수정
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String projectId = request.getParameter("projectid");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        ProjectDetail selectedProject = null;
        List<Pay> projectPayList = null;
        List<Level> levelList = null;
        IProjectService projectService = new ProjectService();
        IPayService payService = new PayService();
        ILevelDAO levelDAO = new LevelDAOImpl();

        List<Integer> levelIdList = new ArrayList<>();
        boolean isLiked = false;

        // ✅ DTO로 묶어서 보낼 리스트 생성
        List<PayLevelDTO> payLevelList = new ArrayList<>();

        try {
            selectedProject = projectService.selectProjectById(Integer.parseInt(projectId));
            projectPayList = payService.selectPayByProjectId(Integer.parseInt(projectId));

            for (Pay pay : projectPayList) {
                levelIdList.add(pay.getLvId());
            }

            levelList = levelDAO.levelList(levelIdList);

            // ✅ Pay와 Level을 묶는 로직
            for (int i = 0; i < projectPayList.size(); i++) {
                Pay pay = projectPayList.get(i);
                Level level = levelList.get(i); // lvId와 levelId 순서가 같다고 가정
                payLevelList.add(new PayLevelDTO(pay, level));
            }

            if(freelancerId != null) {
                isLiked = projectService.isProjectLiked(freelancerId, Integer.parseInt(projectId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ 새로운 payLevelList 전달
        System.out.println("Project : " + selectedProject);
        request.setAttribute("projectPayList", projectPayList);
        request.setAttribute("payLevelList", payLevelList);
        request.setAttribute("userId", freelancerId);
        request.setAttribute("project", selectedProject);
        request.setAttribute("projectId", projectId);
        request.setAttribute("isLiked", isLiked);

        request.getRequestDispatcher("/home/project_detail.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
