package controller;

import dto.Freelancer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/freelancer")
public class FreeLancerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeLancerController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerid = request.getParameter("freelancerid");

        Freelancer freelancer = new Freelancer(
                freelancerid,
                "홍길동",
                "길동이",
                Date.valueOf("2024-10-01"),
                "서울대학교 컴퓨터공학과",
                "Java, Spring, MyBatis, React"
        );

        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("/admin/freelancer_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
