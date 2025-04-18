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

    @Override
    public Integer insertQnA(QnA qna) throws Exception {
        Integer result = sqlSession.insert("mapper.qna.insertQna", qna);
        sqlSession.commit(); // INSERT는 반드시 commit 해줘야 반영됨
        return result;
    }
}
