package controller.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/getCategoryForApply")
public class GetCategoryForApply extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetCategoryForApply() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

     /*       throws ServletException, IOException {

        String projectIdStr = request.getParameter("projectId");
        int projectId;

        try {
            projectId = Integer.parseInt(projectIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 프로젝트 ID");
            return;
        }

        List<Position> positions;

        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            positions = sqlSession.selectList(
                    "mapper.pay.selectPositionsByProjectId", projectId
            );
        }

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(positions));
        out.close();*/
    }
}