package controller.home;

import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;
import util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/portfolio-list")
public class PortfolioList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        IPortfolioService service = new PortfolioService();
        String freelancerId = request.getParameter("freelancerid");
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
            request.getRequestDispatcher("/home/portfolio_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오 목록 조회를 실패했습니다.");
            request.getRequestDispatcher("/home/portfolio_list.jsp").forward(request, response);
        }
    }
}