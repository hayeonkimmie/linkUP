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
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Project> catalogProjectByCategory(String category) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.catalogProjectByCategory", category);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.searchProjectsByCategoryAndKeyword", param);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Project> catalogProjectByConditions(Map<String, String> param) {
        try (SqlSession session = sqlSession.openSession()) {
            return session.selectList("mapper.project.catalogProjectByConditions", param);
        }catch (Exception e) {
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
}
