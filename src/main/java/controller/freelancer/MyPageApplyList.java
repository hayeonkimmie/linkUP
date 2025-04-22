package controller.freelancer;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/apply-proj-list")
public class MyPageApplyList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageApplyList(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = request.getParameter("userId");
        freelancerId = "free002";
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("page = " + pageStr);
        PageInfo pageInfo = new PageInfo(page);


        request.getRequestDispatcher("/freelancer/apply_proj_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/freelancer/apply_proj_list.jsp").forward(request, response);
    }
}