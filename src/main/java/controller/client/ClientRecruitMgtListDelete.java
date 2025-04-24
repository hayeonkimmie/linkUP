package controller.client;

import service.client.IProjectMgtService;
import service.client.ProjectMgtServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/deleteProject")
public class ClientRecruitMgtListDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientRecruitMgtListDelete() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final IProjectMgtService service = new ProjectMgtServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        String idStr = request.getParameter("projectId");

        try {
            int projectId = Integer.parseInt(idStr);
            service.deleteProject(projectId);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
        }
    }
}

