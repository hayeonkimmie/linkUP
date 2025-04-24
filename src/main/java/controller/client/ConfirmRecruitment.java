package controller.client;

import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

// 구인자 마이페이지 '내 프로젝트 조회'에서 구인중 > 구인확정 누르면 구인중에 있던 리스트가 '시작전'으로 넘어감
@WebServlet("/confirmRecruitment")
public class ConfirmRecruitment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConfirmRecruitment() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // service호출을 위해 선언
    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    // 데이터 전송하는 것이므로 doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectIdParam = request.getParameter("projectId"); // projectId검증
        if(projectIdParam == null){ //projectId 값이 없으면
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Projecct ID 없음!");
            return; // 오류메시지 보내고 종료
        }
        try {
            int projectId = Integer.parseInt(projectIdParam); // NULL이 아니라 int 숫자 값을 받아오면,
            service.updateProjectStatusToConfirmed(projectId); //projectId 전달하면서 서비스 호출
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "구인 확정 중 오류 발생");
        }
    }
}
