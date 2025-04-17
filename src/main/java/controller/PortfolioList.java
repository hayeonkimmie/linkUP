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
        String userId = request.getParameter("id");
        String page_str = request.getParameter("page");
        Integer page = null;
        if(page_str == null) {
            page = 1;
        } else {
            page = Integer.parseInt(page_str);
        }

        PageInfo pageInfo = new PageInfo(page);
        IPortfolioService service = new PortfolioService();
        userId = "A";
        List<Portfolio> portfolioList;
        try {
            Integer portfolioCnt = service.selectPortfolioCnt(userId);
            if (portfolioCnt > 0) {
                portfolioList = service.selectPortfolioListByPage(pageInfo, userId);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("portfolioList", null);
            } else if (portfolioCnt == 0){
                request.setAttribute("portfolioList", "등록된 포트폴리오가 없습니다.");
            }
            request.getRequestDispatcher("/freelancer/my_page_portfolio_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오 목록 조회를 실패했습니다.");
            request.getRequestDispatcher("/freelancer/my_page_portfolio_list.jsp").forward(request, response);
//            request.getRequestDispatcher("/freelancer/error.jsp").forward(request, response);
        }
    }
}