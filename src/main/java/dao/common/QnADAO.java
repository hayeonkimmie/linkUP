package dao.common;

import dto.QnA;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QnADAO implements IQnADAO{
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();


    @Override
    public Integer selectQnACnt(String userId) {
        return sqlSession.selectOne("mapper.qna.selectQnACntByUserId", userId);
    }

    @Override
    public List<QnA> selectQnAListByPage(Integer row, String userId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("row", row);
        return sqlSession.selectList("mapper.qna.selectQnAListByPage", param);
    }
}
