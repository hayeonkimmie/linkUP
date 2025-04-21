package dao.common;

import dto.QnA;

import java.util.List;

public interface IQnADAO {
    public Integer selectQnACnt(String userId);
    public List<QnA> selectQnAListByPage(Integer row, String userId) throws Exception;
    public Integer insertQnA(QnA qna) throws Exception;
}
