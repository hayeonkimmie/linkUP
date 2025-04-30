package dao.home;

import dto.Level;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory; // 네가 사용하는 factory
import java.util.List;

public class LevelDAOImpl implements ILevelDAO {

    private final SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Level> selectAllLevel() {
        return sqlSession.selectList("mapper.level.selectAllLevel");
    }
}