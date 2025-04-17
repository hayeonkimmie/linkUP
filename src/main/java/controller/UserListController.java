package controller;

import dto.Client;
import dto.Freelancer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/users")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Freelancer> freelancerList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();
//        clientList.add(new Client(
//                1,
//                "techcorp2",
//                "info@techcorp.kr",
//                "테크놀로지 주식회사",
//                "010-1234-5678",
//                "구인자",
//                Date.valueOf("2010-01-01"),
//                "김민수",
//                "소프트웨어 개발",
//                "서비스업",
//                "서울시 강남구 역삼동",
//                "123-45-67890",
//                "02-1234-5679"
//        ));
//        clientList.add(new Client(
//                2,
//                 "sample23",
//                "sample@sample.com",
//                "글로벌 솔루션즈",
//                "010-1234-5678",
//                "구인자",
//                 Date.valueOf("2010-01-01"),
//                "홍길동",
//                "소프트웨어 개발",
//                "융합 소프트웨어",
//                "서울시 강남구 역삼동",
//                "123-45-67890",
//                "02-1234-5679"
//        ));

        freelancerList.add(new Freelancer(
                "1",
                "김철수",
                "kim33@example.com",
                Date.valueOf("2023-02-05"),
                "010-1234-5678",
                "구직자"
        ));

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("clientList", clientList);
        request.setAttribute("freelancerList", freelancerList);

        request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
