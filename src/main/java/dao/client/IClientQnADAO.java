package dao.client;

import dto.QnA;
import util.PageInfo;

import java.util.List;
import java.util.Map;

public interface IClientQnADAO {
    public Integer selectQnACount() throws Exception;
    public List<QnA> selectQnAList(Integer row) throws Exception;
    public Integer selectQnACountWithFilter(Map<String, Object> param) throws Exception;
    List<QnA> selectQnAListWithFilter(Map<String, Object> param) throws Exception;

    // 사용자 ID 기반 메소드 추가
    public Integer selectQnACountByUserId(String userId) throws Exception;
    public List<QnA> selectQnAListByUserId(Map<String, Object> param) throws Exception;
}



