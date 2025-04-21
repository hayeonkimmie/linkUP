package dao.freelancer;

import dto.Career;
import dto.Freelancer;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreelancerDAO implements IFreelancerDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    public Freelancer selectBasicFreelancerById(String userId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectBasicFreelancerById", userId);
    }
    public Freelancer selectExpertFreelancerById(String userId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectExpertFreelancerById", userId);
    }
    public Map<Integer, String> selectAllportfolioInfoMap(String userId) throws Exception {
        return sqlSession.selectMap("mapper.portfolio.portfolioInfoMap", userId, "project_id");
    }
    public List<Career> selectCareerById(String userId) throws Exception{
        List<Career> careerList = new ArrayList<Career>();
        try {
            careerList = sqlSession.selectList("mapper.freelancer.selectCareerById", userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return careerList;
    }

    @Override
    public void insertFreelancer(Freelancer freelancer) throws Exception {

    }
    @Override
    public Career insertCareer(Career career) throws Exception {

        return career;
    }
    @Override
    public void modifyFreelancer(Freelancer freelancer) throws Exception {
        System.out.println("DAO 47 + updateUserProfile"+freelancer);
        sqlSession.update("mapper.freelancer.updateUserProfile", freelancer);
        System.out.println("DAO 47 + modifyFreelancer"+freelancer);
        sqlSession.update("mapper.freelancer.updateFreelancerProfile", freelancer);
    }
    @Override
    public void modifyCareer(Career career) throws Exception {

    }
    @Override
    public void deleteCareer(String freelancerId) throws Exception {

    }
}
