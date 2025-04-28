package dao.common;

import dto.Freelancer;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class CatalogFreelancerDAOImpl implements ICatalogFreelancerDAO {

    @Override
    public List<Freelancer> catalogFreelancersByCategory(int categoryId) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.catalogFreelancersByCategory", categoryId);
        }
    }

    @Override
    public List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.searchFreelancersByCategoryAndKeyword", params);
        }
    }
    @Override
    public List<Freelancer> catalogFreelancersBySubCategoryIds(List<Integer> subCategoryIds) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.catalogFreelancersBySubCategoryIds", subCategoryIds);
        }
    }

    @Override
    public List<Freelancer> findAllFreelancers() {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.freelancer.findAllFreelancers");
        }
    }

}
