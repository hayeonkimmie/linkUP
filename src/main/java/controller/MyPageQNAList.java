package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/qna-list")
public class MyPageQNAList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageQNAList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
/*        String id = request.getParameter("id");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);*/
        request.getRequestDispatcher("/freelancer/my_page_qna_list.jsp").forward(request, response);
    }
}