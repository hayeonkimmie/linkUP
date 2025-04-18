package dao.admin;

import dto.AdminProject;
import dto.AdminProjectDetail;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class ProjectDAO implements IProjectDAO {
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<AdminProject> selectAllOngoingProject() throws Exception {
        return sqlSession.selectList("mapper.aproject.selectAllOngoingProject");
    }

    @Override
    public AdminProjectDetail selectProjectDetail(int projectId) throws Exception {
        return sqlSession.selectOne("mapper.aproject.selectProjectDetail", projectId);
    }


}
