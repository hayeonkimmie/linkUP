package controller.freelancer;

import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/my-page/portfolio-detail")
public class PortfolioDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PortfolioDetail() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기

        IPortfolioService service = new PortfolioService();

        Integer portfoId = Integer.parseInt(request.getParameter("id"));
        System.out.println("포트폴리오 상세보기 id = " + portfoId);
        try {
            if(!(service.isPortfolioOwner(freelancerId, portfoId))) {
                System.out.println("작성자 여부"+service.isPortfolioOwner(freelancerId, portfoId));
                request.setAttribute("err", "작성자에게만 공개된 포트폴리오 입니다.");
            } else {
                Portfolio portfolio = service.selectPortfolioById(portfoId);
                if(portfolio == null) {
                    request.setAttribute("err", "포트폴리오 조회에 실패했습니다.");
                } else {
                    if (portfolio.getIsDeleted()) {
                        request.setAttribute("err", "삭제된 포트폴리오는 조회 할 수 없습니다.");
                    } else {
                        System.out.println(portfolio);
                        request.setAttribute("portfolio", portfolio);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "포트폴리오 조회에 실패했습니다.");
        }
        request.getRequestDispatcher("/freelancer/portfolio_detail.jsp").forward(request, response);
    }
}