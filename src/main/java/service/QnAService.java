package service;

import dao.common.IQnADAO;
import dao.common.QnADAO;
import dto.QnA;
import util.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class QnAService implements IQnAService {
private IQnADAO iQnADAO;
    public QnAService() {
        super();
        iQnADAO = new QnADAO();
    }

    @Override
    public Integer selectQnACnt(String userId) {
        return iQnADAO.selectQnACnt(userId);
    }

    @Override
    public List<QnA> selectQnAListByPage(PageInfo pageInfo, String userId) {
        Integer qnaCnt = iQnADAO.selectQnACnt(userId);
        if(qnaCnt == null) {
            return null;
        }
        System.out.println("QnAService.java 29 qnaCnt = " + qnaCnt);
        Integer allPage = (int)Math.ceil((double)qnaCnt/10);
        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        System.out.println("QnAService.java 38 pageInfo.getCurPage() = " + pageInfo.getCurPage());

        Integer row = (pageInfo.getCurPage()-1)*10+1;

        List<QnA> qnaList = null;
        try {
            qnaList = iQnADAO.selectQnAListByPage(row-1, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("QnAService.java 29 qnaList = " + qnaList);
        return qnaList;
    }

    @Override
    public Integer insertQnA(QnA qna) throws Exception {
        return iQnADAO.insertQnA(qna);
    }
}
