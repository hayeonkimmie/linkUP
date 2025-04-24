package dao.common;

import dto.Project;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class ProjectDAO implements IProjectDAO {

    @Override
    public List<Project> MainProjectsByCategory(String category) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.project.MainProjectsByCategory", category);
        }
    }
    @Override
    public List<Project> catalogProjectByCategory(String category) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.project.catalogProjectByCategory", category);
        }
    }
    @Override
    public List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.project.searchProjectsByCategoryAndKeyword", param);
        }
    }
    @Override
    public List<Project> catalogProjectByConditions(Map<String, String> param) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.selectList("mapper.project.catalogProjectByConditions", param);
        }
    }
}
