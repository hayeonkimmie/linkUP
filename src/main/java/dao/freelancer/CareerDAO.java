package dao.freelancer;

import dto.Career;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class CareerDAO implements ICareerDAO{
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Career> selectCareerById(String freelancerId) throws Exception {
        return sqlSession.selectList("mapper.freelancer.selectCareerListById", freelancerId);
    }

    @Override
    public void insertCareer(Career career) throws Exception {

    }

    @Override
    public void updateCareer(Career career) throws Exception {

    }

    @Override
    public void deleteCareer(Integer num) throws Exception {

    }
}
