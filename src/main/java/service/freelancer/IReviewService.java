package service.freelancer;

import dto.Review;
import util.PageInfo;

import java.util.List;

public interface IReviewService {
    void writeReview(Review review);
    void updateReview(Review review) throws Exception;
    public List<Review> getReceivedReviewListById(PageInfo pageInfo, String freelancerId) throws Exception;
    public List<Review> getWrittenReviewListById(PageInfo pageInfo, String freelancerId) throws Exception;

    public Integer receivedReviewCnt(String userId) throws Exception;
    public Integer writtenReviewsCnt(String userId) throws Exception;

    boolean isReviewWriter(String userId, Integer reviewId) throws Exception;
    void deleteReview(Integer reviewId)throws Exception;
}
