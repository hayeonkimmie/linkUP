package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/company")
public class Company extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Company() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String companyIdParam = request.getParameter("companyid");
        int companyId = companyIdParam != null ? Integer.parseInt(companyIdParam) : -1;

        // 임시 CompanyDTO 생성 (DB 연동 가능)

        dto.Company company = new dto.Company(
                companyId,
                "sample@sample.com",
                "sample",
                Date.valueOf("2021-10-12"),
                "010-1234-5678",
                "구인자",
                Date.valueOf("2010-01-01"),
                "홍길동",
                "IT",
                "소프트웨어 개발",
                "서울시 강남구 역삼동");

        request.setAttribute("company", company);
        request.getRequestDispatcher("/admin/company_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
