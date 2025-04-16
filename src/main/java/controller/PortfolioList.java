package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import dto.Portfolio;

import service.IPortfolioService;
import service.PortfolioService;
import util.PageInfo;


@WebServlet("/my-page/portfolio-list")
public class PortfolioList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String page_str = request.getParameter("page");
        Integer page = null;
        if(page_str == null) {
            page = 1;
        } else {
            page = Integer.parseInt(page_str);
        }

        PageInfo page_info = new PageInfo(page);
        IPortfolioService service = new PortfolioService();
        try {
            List<Portfolio> portfolio_list = service.select_portfolio_list_by_page(page_info, "A");
            request.setAttribute("page_info", page_info);
            request.setAttribute("portfolio_list", portfolio_list);
            request.getRequestDispatcher("/freelancer/portfolio_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "게시판 목록 조회를 실패했습니다.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }


    }
}