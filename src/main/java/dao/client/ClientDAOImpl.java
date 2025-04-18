package dao.client;

import dto.QnA;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class ClientDAOImpl implements IClientDAO {
    private SqlSession sqlSession= MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<QnA> selectQnAList(Integer row) throws Exception {
        return sqlSession.selectList("mapper.qna.selectQnAList", row);
    }

    @Override
    public Integer selectQnACount() throws Exception {
        return sqlSession.selectOne("mapper.qna.selectQnACount");
    }
}
