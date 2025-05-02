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
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        IPortfolioService service = new PortfolioService();
        try {
            if (!service.isPortfolioOwner(freelancerId, portfolioId)) {
                request.setAttribute("err", "본인이 작성하지 않은 포트폴리오를 수정할 수 없습니다.");
                response.sendRedirect(request.getContextPath() + "/my-page/portfolio-list");
            }
            System.out.println("portfolioId: " + portfolioId);
            service.deletePortfolio(portfolioId);
            request.setAttribute("result", "포트폴리오 삭제 성공");

        } catch (Exception e) {
            request.setAttribute("result", "포트폴리오 삭제에 실패했습니다.");
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/my-page/portfolio-list");
    }
}