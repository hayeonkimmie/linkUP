package service.freelancer;

import dao.freelancer.IReviewDAO;
import dao.freelancer.ReviewDAO;
import dto.Review;
import util.PageInfo;

import java.util.List;

public class ReviewService implements IReviewService{
    private IReviewDAO iReviewDAO;
    public ReviewService() {
        super();
        iReviewDAO = new ReviewDAO();
    }
    @Override
    public void writeReview(Review review) {
        iReviewDAO.writeReview(review);
    }

    @Override
    public void updateReview(Review review) throws Exception {
        iReviewDAO.updateReview(review);
    }

    @Override
    public List<Review> getReceivedReviewListById(PageInfo pageInfo, String freelancerId) throws Exception {
        Integer receivedReviewsCnt = iReviewDAO.receivedReviewCnt(freelancerId);
        System.out.println("PortfolioService.java 28 receivedReviewsCnt "+receivedReviewsCnt);
        if(receivedReviewsCnt == null || receivedReviewsCnt == 0) {
            return null;
        }
        Integer allPage = (int)Math.ceil((double)receivedReviewsCnt/10);
        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage()-1)*10+1;
        System.out.println("PortfolioService.java 38 row "+row);
        try {
            return iReviewDAO.getReceivedReviewListById(row, freelancerId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getWrittenReviewListById(PageInfo pageInfo, String freelancerId) throws Exception {
        Integer writtenReviewsCnt = iReviewDAO.writtenReviewsCnt(freelancerId);
        System.out.println("PortfolioService.java 49 writtenReviewsCnt "+writtenReviewsCnt);
        if(writtenReviewsCnt == null || writtenReviewsCnt == 0) {
            return null;
        }
        Integer allPage = (int)Math.ceil((double)writtenReviewsCnt/10);
        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1;
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage()-1)*10+1;
        System.out.println("PortfolioService.java 64 row "+row);
        return iReviewDAO.getWrittenReviewListById(row, freelancerId);
    }
    @Override
    public Integer receivedReviewCnt(String userId) throws Exception {
        return iReviewDAO.receivedReviewCnt(userId);
    }
    @Override
    public Integer writtenReviewsCnt(String userId) throws Exception {
        return iReviewDAO.writtenReviewsCnt(userId);
    }
    @Override
    public boolean isReviewWriter(String userId, Integer reviewId) throws Exception {
        return iReviewDAO.isReviewWriter(userId, reviewId);
    }

    @Override
    public void deleteReview(Integer reviewId) throws Exception {
        iReviewDAO.deleteReview(reviewId);
    }

}