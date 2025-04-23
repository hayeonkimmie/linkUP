package dao.common;

import dto.Project;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class ProjectDAO implements IProjectDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Project> MainProjectsByCategory(String category) {
        return sqlSession.selectList("mapper.project.MainProjectsByCategory", category);
    }
}
