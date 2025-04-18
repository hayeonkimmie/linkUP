package dao.admin;

import dto.Freelancer;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class FreelancerDAO implements IFreelancerDAO{
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Freelancer> selectAllFreelancer() throws Exception {
        return sqlSession.selectList("mapper.freelancer.selectAllFreelancer");
    }
}
