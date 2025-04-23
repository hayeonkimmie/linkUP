package dao.home;

import dto.Client;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class SearchCompanyDAOImpl implements ISearchCompanyDAO {
    @Override
    public List<Client> searchCompanyByName(String keyword) throws Exception {
        // ✅ 메소드 안에서 열기
        SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            return sqlSession.selectList("mapper.client.searchCompanyByName", keyword);
        } finally {
            sqlSession.close(); // ✅ 메소드 끝날 때 닫기
        }
    }
}
