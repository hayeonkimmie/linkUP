package controller.freelancer;

import service.freelancer.IReviewService;
import service.freelancer.ReviewService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/review-delete")
public class MyPageReviewDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageReviewDelete() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer reviewId = Integer.parseInt(request.getParameter("id"));
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        }
        freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        try {
            IReviewService service = new ReviewService();
            if(reviewId == null) {
                request.setAttribute("err", "프로젝트 후기 ID가 없습니다.");
                request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
            } else if(!service.isReviewWriter(freelancerId, reviewId)) {
                request.setAttribute("err", "본인이 작성하지 않은 프로젝트 후기를 삭제할 수 없습니다.");
                request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
            }else {
                    service.deleteReview(reviewId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
    }
}