//package controller.client;
//
//import dto.ProjectMgt;
//import service.client.IProjectMgtService;
//import service.client.ProjectMgtServiceImpl;
//import util.PageInfo;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/clientRecruitMgtAjax")
//public class ClientRecruitMgtAjax extends HttpServlet {
//    private final IProjectMgtService service = new ProjectMgtServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String clientId = (String) request.getSession().getAttribute("userId");
//        if (clientId == null) clientId = "client001";
//
//        String status = request.getParameter("status");
//        if (status == null) status = "all";
//
//        int curPage = 1;
//        try {
//            curPage = Integer.parseInt(request.getParameter("page"));
//        } catch (Exception ignored) {}
//
//        Map<String, Object> param = new HashMap<>();
//        param.put("clientId", clientId);
//        param.put("status", status);
//
//        Map<String, Object> resultMap = null;
//        try {
//            resultMap = service.getPagedProjectList(param, curPage);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        List<ProjectMgt> projectList = (List<ProjectMgt>) resultMap.get("projectList");
//        PageInfo pageInfo = (PageInfo) resultMap.get("pageInfo");
//
//        request.setAttribute("projectList", projectList);
//        request.setAttribute("pageInfo", pageInfo);
//        request.setAttribute("status", status);
//
//        // 이 JSP는 카드 목록과 페이징 UI만 렌더링 (main이 아님)
//        request.getRequestDispatcher("/client/include/projectCardList.jsp").forward(request, response);
//    }
//}
//
