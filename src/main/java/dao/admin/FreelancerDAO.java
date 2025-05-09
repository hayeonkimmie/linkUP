package dao.admin;

import dto.AdminFreelancer;
import dto.Freelancer;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class FreelancerDAO implements IFreelancerDAO{
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Freelancer> selectAllFreelancer() throws Exception {
        return sqlSession.selectList("mapper.afreelancer.selectAllFreelancer");
    }

    @Override
    public AdminFreelancer selectFreelancerById(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.afreelancer.selectFreelancerById", freelancerId);
    }

    @Override
    public List<Freelancer> searchFreelancersByKeyword(String keyword) throws Exception {
        return sqlSession.selectList("mapper.afreelancer.searchFreelancersByKeyword", "%"+keyword+"%");
    }
}
