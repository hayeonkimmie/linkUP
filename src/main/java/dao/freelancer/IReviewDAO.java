package dao.freelancer;

import dto.Review;
import java.util.List;
public interface IReviewDAO {
    void writeReview(Review review);
    List<Review> getReceivedReviewListById(Integer row, String freelancerId) throws Exception;
    List<Review> getWrittenReviewListById(Integer row, String freelancerId) throws Exception;

    Integer writtenReviewsCnt(String userId) throws Exception;
    Integer receivedReviewCnt(String userId) throws Exception;

    void updateReview(Review review) throws Exception;

    boolean isReviewWriter(String userId, Integer reviewId) throws Exception;

    void deleteReview(Integer reviewId) throws Exception;
}
