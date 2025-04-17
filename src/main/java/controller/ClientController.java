package controller;

import dto.Client;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/client")
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdParam = request.getParameter("clientid");
        String clientId = clientIdParam != null ? clientIdParam : "sampleId"; // 기본값 설정
        System.out.println(clientId);
        // 임시 CompanyDTO 생성 (DB 연동 가능)

//        Client client = new Client(
//                1,
//                clientId,
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
//        );

//        request.setAttribute("client", client);
        request.getRequestDispatcher("/admin/client_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
