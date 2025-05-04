package dao.common;

import dto.Project;
import dto.ProjectDetail;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.MybatisSqlSessionFactory;
import util.SingleTonSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDAO implements IProjectDAO {

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

    @Override
    public List<Project> MainProjectsByCategory(String category) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.MainProjectsByCategory", category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> catalogProjectByCategory(String category) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.catalogProjectByCategory", category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.searchProjectsByCategoryAndKeyword", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> catalogProjectByConditions(Map<String, String> param) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.catalogProjectByConditions", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectDetail selectProjectById(Integer projectId) throws Exception {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectOne("mapper.project.selectProjectById", projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertProject(Project project) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            sqlSession.insert("mapper.project.insertProject", project);
            sqlSession.commit(); // ⭐ INSERT할 때는 반드시 commit 해야 DB에 저장된다
        }
    }

    @Override
    public List<Project> getProjectById(int projectId) throws Exception {
        return null;
    }

    @Override
    public Project selectProjectByProjectId(Integer projectId) throws Exception {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return session.selectOne("mapper.project.selectProjectByProjectId", projectId);
        }
    }


}