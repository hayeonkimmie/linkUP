package dao.home;

import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class CategoryDAOImpl implements ICategoryDAO {
    @Override
    public List<Integer> findSubCategoryIdsByCategoryName(String categoryName) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.category.findSubCategoryIdsByCategoryName", categoryName);
        }
    }
}
