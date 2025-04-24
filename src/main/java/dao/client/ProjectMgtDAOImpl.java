package dao.client;

import dto.ProjectMgt;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class ProjectMgtDAOImpl implements IProjectMgtDAO {

    private final SqlSession sqlSession;

    //기본 생성자
    public ProjectMgtDAOImpl() {
        this.sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    }

    @Override
    public List<ProjectMgt> selectProjectMgtByStatus(Map<String, Object> param) throws Exception {
        return sqlSession.selectList("mapper.projectmgt.selectProjectMgtByStatus", param);
    }

    @Override
    public void updateStatusToConfirmed(int projectId) throws Exception {
        // try 사용함으로써 commit()후 자동으로 session.close() 호출하기
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            session.update("mapper.projectmgt.updateStatusToConfirmed", projectId);
            session.commit();
        }
    }

    @Override
    public void deleteProject(int projectId) throws Exception {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()){
            session.delete("mapper.projectmgt.deleteProject", projectId);
            session.commit();
        }
    }
}

