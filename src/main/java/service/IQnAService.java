package service;

import dto.QnA;
import util.PageInfo;

import java.util.List;

public interface IQnAService {
    Integer selectQnACnt(String userId) throws Exception;
    List<QnA> selectQnAListByPage(PageInfo pageInfo, String userId) throws Exception;
    Integer insertQnA(QnA qna) throws Exception;
}
