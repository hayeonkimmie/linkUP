package dao.client;

import dto.QnA;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;
import util.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientQnADAOImpl implements IClientQnADAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
        @Override
        public Integer selectQnACount() throws Exception {
            return sqlSession.selectOne("mapper.qna.selectQnACount");
        }

        @Override
        public List<QnA> selectQnAList(Integer row) throws Exception {
            return sqlSession.selectList("mapper.qna.selectQnAList", row);
        }

        @Override
        public Integer selectQnACountWithFilter(Map<String, Object> param) throws Exception {
            return sqlSession.selectOne("mapper.qna.selectQnACountWithFilter", param);
        }

        @Override
        public List<QnA> selectQnAListWithFilter(Map<String, Object> param) throws Exception {
            return sqlSession.selectList("mapper.qna.selectQnAListWithFilter", param);
        }

        @Override
        public Integer selectQnACountByUserId(String userId) throws Exception {
            return sqlSession.selectOne("mapper.qna.selectQnACntByUserId", userId);
        }

        @Override
        public List<QnA> selectQnAListByUserId(Map<String, Object> param) throws Exception {
            return sqlSession.selectList("mapper.qna.selectQnAListByPage", param);
        }
    }


