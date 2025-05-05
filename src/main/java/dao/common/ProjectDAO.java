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
import java.util.Objects;

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
    public boolean isProjectLiked(String freelancerId, Integer projectId) throws Exception {
        try (SqlSession session = sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("freelancerId", freelancerId);
            param.put("projectId", projectId);
            Integer cnt = session.selectOne("mapper.jjimProj.isProjectLiked", param);
            return cnt == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer likeProject(String freelancerId, Integer projectId) {
        System.out.println("dao 도착.java 85" + freelancerId + " " + projectId);
        try (SqlSession session = sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("freelancerId", freelancerId);
            System.out.println("likeProject.java 89" + param);
            int result = session.insert("mapper.jjimProj.insertJjimProj", param);
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void cancelProjectLike(String freelancerId, Integer projectId) {
        try (SqlSession session = sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("freelancerId", freelancerId);
            param.put("projectId", projectId);
            System.out.println("cancelProjectLike.java 100" + param);
            session.delete("mapper.jjimProj.cancelProjectLike", param);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
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