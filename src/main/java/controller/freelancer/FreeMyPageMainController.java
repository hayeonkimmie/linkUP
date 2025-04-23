package controller.freelancer;

import dto.JjimProj;
import service.freelancer.IJjimProjService;
import service.freelancer.JjimProjService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page")
public class FreeMyPageMainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageMainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        session.setAttribute("id", id);
        IJjimProjService service = new JjimProjService();
        List<JjimProj> jjimProjList;
        try{
            Integer jjimProjCnt = service.selectJjimProjCnt(id);
            if (jjimProjCnt > 0) {
                jjimProjList = service.selectJjimProjListForMain(id);
                System.out.println("JJimProjList 서블릿 47 JJimProjList = " + jjimProjList);
                request.setAttribute("jjimProjList", jjimProjList);
            } else if (jjimProjCnt == 0){
                request.setAttribute("jjimProjList", null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "찜한 프로젝트 조회에 실패했습니다.");
        }
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
    }
}