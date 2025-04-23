package controller.home;

import dto.Client;
import service.home.ISearchCompanyService;
import service.home.SearchCompanyServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchCompany")
public class SearchCompanyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ISearchCompanyService companyService = new SearchCompanyServiceImpl();

    public SearchCompanyController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        System.out.println("검색어 확인: " + keyword);
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                // 검색어 없으면 그냥 결과 없음 처리
                request.setAttribute("companies", null);
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("/home/searchCompany.jsp").forward(request, response);
                return;
            }

            List<Client> companies = companyService.searchCompanyByName(keyword);
            request.setAttribute("companies", companies);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("/home/searchCompany.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
