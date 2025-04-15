package controller;

import dto.PrepareSettlement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/client-list")
public class ClientListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String comapnyid = request.getParameter("companyid");
        List<PrepareSettlement> settlements = new ArrayList<>();
        settlements.add(new PrepareSettlement(
                "서비스 리뉴얼", // projectName
                "2023-10-01 ~ 2023-10-31", // projectDuration
                "홍길동", // projectManager
                "010-1234-5678", // managerPhone
                1000000, // totalAmount
                3000, // totalFee
                1003000, // totalSettlement
                5, // participant
                Date.valueOf("2023-10-31").toLocalDate().getDayOfMonth(), // settleDate
                "정산완료" // settleStatus
        ));
        settlements.add(new PrepareSettlement(
                "웹사이트 개발", // projectName
                "2023-09-01 ~ 2023-09-30", // projectDuration
                "이순신", // projectManager
                "010-2345-6789", // managerPhone
                2000000, // totalAmount
                5000, // totalFee
                2005000, // totalSettlement
                10, // participant
                Date.valueOf("2023-09-30").toLocalDate().getDayOfMonth(), // settleDate
                "정산대기" // settleStatus
        ));

        request.setAttribute("settlements", settlements);
        request.getRequestDispatcher("/admin/client_project_list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
