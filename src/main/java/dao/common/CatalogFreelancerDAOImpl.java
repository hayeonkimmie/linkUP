package dao.common;

import dto.Freelancer;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class CatalogFreelancerDAOImpl implements ICatalogFreelancerDAO {
    @Override
    public List<Freelancer> catalogFreelancersByCategory(String category) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.catalogFreelancersByCategory", category);
        }
    }

    @Override
    public List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.searchFreelancersByCategoryAndKeyword", params);
        }
    }

}
