package dao.common;


import dto.Category;
import dto.SubCategory;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;
import java.util.List;

public class CategoryMenuDAOImpl implements ICategoryMenuDAO {
    private final SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Category> selectAllCategoriesWithSub() {
        return sqlSession.selectList("mapper.categoryMenuMapper.selectAllWithSub");
    }
}
