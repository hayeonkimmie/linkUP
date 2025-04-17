package dao.admin;

import dto.DashboardProject;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class DashboardProjectDAO implements IDashboardProjectDAO{

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<DashboardProject> selectAllOngoingProjects() {
        return sqlSession.selectList("mapper.dproject.selectAllOngoingProjects");
    }

    @Override
    public DashboardProject getDashboardProject(Integer projectId) {
        return null;
    }
}
