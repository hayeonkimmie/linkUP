package controller.freelancer;

import dto.Review;
import service.freelancer.IReviewService;
import service.freelancer.ReviewService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page/project-review-write")
public class MyPageReviewWrite extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageReviewWrite() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        freelancerId = "free002"; // 로그인 구현 이후 빼기
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        PageInfo pageInfo = new PageInfo(page);
        IReviewService service = new ReviewService();
        List<Review> unWrittenReviewList = null;
        String type = request.getParameter("review");
        Integer unWrittenReviewCnt = 0;
        try {
            unWrittenReviewCnt = service.receivedReviewCnt(freelancerId);
            unWrittenReviewList = service.getUnWrittenReviewListById(pageInfo, freelancerId);
            System.out.println("MyPageReview.java 44 unWrittenReviewList = " + unWrittenReviewList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("page", page);
        request.setAttribute("unWrittenReviewCnt", unWrittenReviewCnt);
        request.setAttribute("unWrittenReviewList", unWrittenReviewList);
        request.getRequestDispatcher("/freelancer/review_write.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyPageReview.java 75 doPost() called");
        request.setCharacterEncoding("UTF-8");

        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
            return;
        }

        String reviewIdStr = request.getParameter("reviewId");
        if (reviewIdStr == null || reviewIdStr.isEmpty()) {
            request.setAttribute("err", "후기 ID가 없습니다.");
            request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
            return;
        }

        try {
            Integer reviewId = Integer.parseInt(reviewIdStr);
            Integer star = Integer.parseInt(request.getParameter("star"));
            String comment = request.getParameter("comment");
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            String rUserId = request.getParameter("rUserId");

            IReviewService service = new ReviewService();

            /*if (!service.isReviewWriter(freelancerId, reviewId)) {
                request.setAttribute("err", "본인이 작성한 후기만 수정할 수 있습니다.");
            } else {*/
                Review review = new Review();
                review.setReviewId(reviewId);
                review.setStar(star);
                review.setComment(comment);
                review.setProjectId(projectId);
                review.setwUserId(freelancerId);
                service.writeReview(review); // 실제 DB 업데이트
                request.setAttribute("msg", "후기 작성이 완료되었습니다.");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "후기 작성 중 오류 발생");
        }
        request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
    }
}