package dao.client;

import dto.Project;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

public class ClientEditProjectDAOImpl implements IClientEditProjectDAO {

    @Override
    public Project selectProjectFixById(int projectId) {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            return session.selectOne("mapper.project.selectProjectFixById", projectId);
        }
    }

    @Override
    public void updateProject(Project project) {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            session.update("mapper.project.updateProject", project);
            session.commit();
        }
    }
}
