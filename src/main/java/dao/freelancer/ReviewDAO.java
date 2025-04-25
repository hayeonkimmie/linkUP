package dao.freelancer;

import dto.Review;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDAO implements IReviewDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public void writeReview(Review review) {
        Map<String, Object> param = new HashMap<>();
        param.put("reviewId", review.getReviewId());
        param.put("star", review.getStar());
        param.put("comment", review.getComment());
        sqlSession.insert("mapper.review.insertReview", param);
        sqlSession.commit();
    }

    @Override
    public void updateReview(Review review) {
        Map<String, Object> param = new HashMap<>();
        param.put("reviewId", review.getReviewId());
        param.put("star", review.getStar());
        param.put("comment", review.getComment());
        param.put("wUserId", review.getwUserId());
        System.out.println("review 31 + "+review);
        sqlSession.update("mapper.review.updateReview", param);
        sqlSession.commit();
    }

    @Override
    public List<Review> getReceivedReviewListById(Integer row, String freelancerId) {
        Map<String, Object> param = new HashMap<>();
        param.put("freelancerId", freelancerId);
        param.put("row", row-1);
        System.out.println("ReviewDAO.java 38 param"+ param);
        return sqlSession.selectList("mapper.review.selectReceiveReviewByFreelancerId", param);
    }

    @Override //작성한 리뷰 목록
    public List<Review> getWrittenReviewListById(Integer row, String freelancerId) {
        Map<String, Object> param = new HashMap<>();
        param.put("freelancerId", freelancerId);
        param.put("row", row-1);
        return sqlSession.selectList("mapper.review.selectWrittenReviewByFreelancerId", param);
    }

    @Override
    public List<Review> getUnWrittenReviewListById(Integer row, String freelancerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("freelancerId", freelancerId);
        param.put("row", row-1);
        List<Review> unWrittenReviewList = sqlSession.selectList("mapper.review.selectUnWrittenReviewByFreelancerId", param);
        System.out.println("ReviewDAO.java 54 unWrittenReviewList"+ unWrittenReviewList);
        return unWrittenReviewList;
    }

    public Integer receivedReviewCnt(String userId) {
        return sqlSession.selectOne("mapper.review.selectReceivedReviewCnt", userId);
    }

    @Override
    public Integer unWrittenReviewCnt(String userId) throws Exception {
        return sqlSession.selectOne("mapper.review.selectUnWrittenReviewsCnt", userId);
    }

    public Integer writtenReviewsCnt(String userId) {
        return sqlSession.selectOne("mapper.review.selectWrittenReviewsCnt", userId);
    }

    @Override
    public boolean isReviewWriter(String userId, Integer reviewId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("reviewId", reviewId);
        Integer result = sqlSession.selectOne("mapper.review.isReviewWriter", param);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteReview(Integer reviewId) throws Exception {
        sqlSession.delete("mapper.review.deleteReviewByReviewId", reviewId);
        sqlSession.commit();
    }
}
