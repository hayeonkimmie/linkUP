package controller.home;

import com.google.gson.Gson;
import service.ProjectService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;

class Response{
    private int projectId;
    private String userId;
    private String position;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Response{" +
                "projectId=" + projectId +
                ", userId='" + userId + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

@WebServlet("/applyProject")
public class ApplyProject extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ApplyProject() {
        super();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonData = sb.toString();
        Gson gson = new Gson();
        Response json = gson.fromJson(jsonData, Response.class);
        ProjectService projectService = new ProjectService();
        System.out.println(json);
        System.out.println(request.getSession().getAttribute("userId"));
        try {
            projectService.createApply(json.getProjectId(), (String) request.getSession().getAttribute("userId"), json.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"status\":\"success\"}");
    }
}