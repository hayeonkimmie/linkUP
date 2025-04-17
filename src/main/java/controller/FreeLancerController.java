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
                "gildong@example.com",
                "010-1234-5678",
                Date.valueOf("2024-10-01"),
                "서울대학교 컴퓨터공학과",
                "Java, Spring, MyBatis, React",
                "연 6,000만원",
                "서울, 재택 가능",
                true,
                "안녕하세요, 백엔드 개발자 홍길동입니다. 다양한 SI/SM 경험이 있습니다.",
                "정보처리기사, AWS CCP",
                "resume_honggildong.pdf",
                "https://portfolio.example.com/gildong",
                "근무 시간 조율 가능했으면 좋겠습니다.",
                "구직자",
                "서울시 강남구"
        );

        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("/admin/freelancer_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
