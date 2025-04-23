package dao.client;

import dto.Client;
import dto.QnA;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class ClientDAOImpl implements IClientDAO {

    @Override
    public List<QnA> selectQnAList(Integer row) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            return sqlSession.selectList("mapper.qna.selectQnAList", row);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public Integer selectQnACount() throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            return sqlSession.selectOne("mapper.qna.selectQnACount");
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public int insertClient(Client client) {
        SqlSession sqlSession = null;
        int result = 0;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            result = sqlSession.insert("mapper.client.insertClient", client);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback();
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return result;
    }

    @Override
    public boolean existsClientById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            Integer count = sqlSession.selectOne("mapper.client.existsClientById", id);
            return (count != null && count > 0);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}



