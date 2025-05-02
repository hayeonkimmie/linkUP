package controller.freelancer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import dto.Portfolio;

import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;
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
        IPortfolioService service = new PortfolioService();
        String freelancerId = (String) request.getSession().getAttribute("userId");
        /*if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }*/
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        PageInfo pageInfo = new PageInfo(page);

        List<Portfolio> portfolioList;
        try {
            Integer portfolioCnt = service.selectPortfolioCnt(freelancerId);
            if (portfolioCnt > 0) {
                portfolioList = service.selectPortfolioListByPage(pageInfo, freelancerId);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("portfolioList", portfolioList);
            } else if (portfolioCnt == 0){
                request.setAttribute("portfolioList", null);
            }
            request.getRequestDispatcher("/freelancer/portfolio_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오 목록 조회를 실패했습니다.");
            request.getRequestDispatcher("/freelancer/portfolio_list.jsp").forward(request, response);
//            request.getRequestDispatcher("/freelancer/error.jsp").forward(request, response);
        }
    }
}