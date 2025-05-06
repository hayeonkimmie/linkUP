package controller.home;

import dto.Freelancer;
import dto.Portfolio;
import service.freelancer.FreelancerService;
import service.freelancer.IFreelancerService;
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


@WebServlet("/portfolio-detail")
public class PortfolioDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioDetail() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        IPortfolioService service = new PortfolioService();
        IFreelancerService freelancerService = new FreelancerService();
        String freelancerId = request.getParameter("freelancerid");
        Integer portfoId = Integer.parseInt(request.getParameter("id"));
        System.out.println("포트폴리오 상세보기 id = " + portfoId);
        try {
            boolean isOwner = service.isPortfolioOwner(freelancerId, portfoId);

            Portfolio portfolio = service.selectPortfolioById(portfoId);
            if (portfolio == null) {
                request.setAttribute("err", "포트폴리오 조회에 실패했습니다.");
            } else if (portfolio.getIsDeleted()) {
                request.setAttribute("err", "삭제된 포트폴리오는 조회할 수 없습니다.");
            } else {
                request.setAttribute("portfolio", portfolio);
            }

            request.getRequestDispatcher("/home/portfolio_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오  조회를 실패했습니다.");
            request.getRequestDispatcher("/home/portfolio_list.jsp").forward(request, response);
        }
    }
}