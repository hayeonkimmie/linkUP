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

@WebServlet("/my-page/project-review")
public class MyPageReview extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageReview() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost() 호출됨");
        request.setCharacterEncoding("UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
/*        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }*/
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
        List<Review> receivedReviewList = null;
        List<Review> writtenReviewList = null;
        List<Review> writeReviewList = null;
        String type = request.getParameter("review");
        Integer receivedReviewCnt = 0;
        Integer writtenReviewCnt = 0;
        try {
            if(type == null || type.equals("") || type.equals("list")) {
                receivedReviewCnt = service.receivedReviewCnt(freelancerId);
                writtenReviewCnt = service.writtenReviewsCnt(freelancerId);

                receivedReviewList = service.getReceivedReviewListById(pageInfo, freelancerId);
                System.out.println("MyPageReview.java 44 receivedReviewList = " + receivedReviewList);
                writtenReviewList = service.getWrittenReviewListById(pageInfo, freelancerId);
                System.out.println("MyPageReview.java 44 reviewList = " + writtenReviewList);
            } else if (type.equals("write")) {
                /*writeReviewList = service.getWrittenReviewListById(pageInfo, freelancerId);
                request.setAttribute("writeReviewList", writeReviewList);
                request.getRequestDispatcher("/freelancer/review_write.jsp").forward(request, response);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("page", page);
        request.setAttribute("receivedReviewCnt", receivedReviewCnt);
        request.setAttribute("writtenReviewCnt", writtenReviewCnt);
        request.setAttribute("receivedReviewList", receivedReviewList);
        request.setAttribute("writtenReviewList", writtenReviewList);
        request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer reviewId = Integer.parseInt(request.getParameter("id"));
        System.out.println("MyPageReview.java 78 reviewId = " + reviewId);
        String freelancerId = (String) request.getSession().getAttribute("userId");
        freelancerId = "free002"; //로그인 기능이 구현된 이후에는 빼기
        if (freelancerId == null) {
            request.setAttribute("err", "로그인 후 이용해주세요.");
            request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
        }
        if(reviewId == null) {
            request.setAttribute("err", "프로젝트 후기 ID가 없습니다.");
            request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
        } else {
            IReviewService service = new ReviewService();
            try {
                if(!service.isReviewWriter(freelancerId, reviewId)) {
                    request.setAttribute("err", "본인이 작성하지 않은 프로젝트 후기를 수정할 수 없습니다.");
                    request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
                } else {
                    Review review =null;
                    request.setAttribute("review", review);
                    Integer star = Integer.parseInt(request.getParameter("star"));

                    String comment = request.getParameter("comment");
                    Integer projectId = Integer.parseInt(request.getParameter("projectId"));
                    String rUserId = request.getParameter("rUserId");
                    review.setStar(star);
                    review.setComment(comment);
                    review.setProjectId(projectId);
                    review.setwUserInfo(freelancerId);
                    System.out.println("MyPageReview.java 88 review = " + review);
                   // Review review = service.updateReview();
                    request.setAttribute("err", "후기 수정이 완료되었습니다.");
                    request.getRequestDispatcher("/freelancer/review_list_update.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}