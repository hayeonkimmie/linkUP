package dao.home;

import dto.Client;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class SearchCompanyDAOImpl implements ISearchCompanyDAO {
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    @Override
    public List<Client> searchCompanyByName(String keyword) throws Exception {
        try {
            return sqlSession.selectList("mapper.client.searchCompanyByName", keyword);
        } finally {
            sqlSession.close();
        }
    }
}
