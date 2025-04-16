package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("my-page/edit-info")
public class FreeMyPageEditInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageEditInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        if(type == null || type.equals("basic")) {
            // 기본 정보 수정
            request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
        } else{
            // 전문가 정보 수정
            request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        // POST 요청 처리

        // 처리 후 화면 리다이렉션
        if(type == null || type.equals("basic")) {
            // 기본 정보 수정
            request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
        } else{
            // 전문가 정보 수정
            request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
        }
    }
}