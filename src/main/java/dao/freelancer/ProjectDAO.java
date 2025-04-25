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
        param.put("freelancerId", freelancerId);
        param.put("row", row-1);
        List<FreelancerProject> projectList = sqlSession.selectList("mapper.freelancer.selectOngoingProjectsByFreelancerId", param);
        System.out.println("ProjectDAO.java 23 projectList = " + projectList);
        return projectList;
    }    @Override
    public List<FreelancerProject> selectOngoingProjectForMain(String freelancerId) throws Exception {
        List<FreelancerProject> projectList = sqlSession.selectList("mapper.freelancer.selectOngoingProjectsForMyPage", freelancerId);
        System.out.println("ProjectDAO.java 28 projectList = " + projectList);
        return projectList;
    }

    @Override
    public List<FreelancerProject> selectCompletedProject(Integer row, String freelancerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("freelancerId", freelancerId);
        param.put("row", row-1);
        List<FreelancerProject> projectList = sqlSession.selectList("mapper.freelancer.selectCompletedProjectsByFreelancerId", param);
        System.out.println("ProjectDAO.java 33 projectList = " + projectList);
        return projectList;
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
