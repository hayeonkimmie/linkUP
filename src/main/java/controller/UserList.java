package controller;

import dto.Company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/users")
public class UserList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Company> companyList = new ArrayList<>();
        companyList.add( new dto.Company(1,"sample@sample.com","sample", Date.valueOf("2021-10-12"),"010-1234-5678","구인자",Date.valueOf("2010-01-01"),"홍길동","IT","소프트웨어 개발","서울시 강남구 역삼동"));
//        companyList.add(new dto.Company(1, "테크놀로지 주식회사", "kim33", "kim33@example.com", "2023-02-05", "010-1234-5678", "구직자"));
        companyList.add(new dto.Company(2,
                "lee33@example.com",
                "글로벌 솔루션즈",
                Date.valueOf("2021-10-12"),
                "010-2345-3456",
                "구인자" ,
                Date.valueOf("2010-01-01"),
                "김길동", "IT",
                "소프트웨어 개발",
                "서울시 강남구 역삼동"));
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("companyList", companyList);
//        request.getRequestDispatcher("/admin/company_detail.jsp").forward(request, response);
        request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
