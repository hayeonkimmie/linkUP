package dao.freelancer;

import dto.AdminProject;
import dto.AdminProjectDetail;
import dto.FreelancerProject;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;
import util.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDAO implements IProjectDAO {
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<FreelancerProject> selectOngoingProject(Integer row, String freelancerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("row", row);
        param.put("freelancerId", freelancerId);
        return sqlSession.selectList("mapper.freelancer.selectOngoingProjectsByFreelancerId", param);
    }

    @Override
    public List<FreelancerProject> selectCompletedProject(Integer row, String freelancerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("row", row);
        param.put("freelancerId", freelancerId);
        return sqlSession.selectList("mapper.freelancer.selectCompletedProjectsByFreelancerId", param);
    }

    @Override
    public int cntOngoingProjects(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.countOngoingProjectsByFreelancerId", freelancerId);
    }

    @Override
    public int cntCompletedProjects(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.cntCompletedProjectsByFreelancerId", freelancerId);
    }

}
