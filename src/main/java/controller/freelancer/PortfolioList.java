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
        String userId = (String) request.getAttribute("userId");
        userId = "free002"; // 로그인 구현 이후 빼기
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        PageInfo pageInfo = new PageInfo(page);

        IPortfolioService service = new PortfolioService();
        List<Portfolio> portfolioList;
        try {
            Integer portfolioCnt = service.selectPortfolioCnt(userId);
            if (portfolioCnt > 0) {
                portfolioList = service.selectPortfolioListByPage(pageInfo, userId);
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