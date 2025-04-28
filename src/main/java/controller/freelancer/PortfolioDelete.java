package controller.freelancer;

import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/portfolio-delete")
public class PortfolioDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioDelete() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(portfolioId);
        IPortfolioService service = new PortfolioService();
        System.out.println("portfolioId: " + portfolioId);
        try {
            service.deletePortfolio(portfolioId);
            request.setAttribute("result", "포트폴리오 삭제 성공");

        } catch (Exception e) {
            request.setAttribute("result", "포트폴리오 삭제에 실패했습니다.");
            throw new RuntimeException(e);
        }
//        request.getRequestDispatcher("/freelancer/portfolio_list.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/my-page/portfolio-list");
    }
}