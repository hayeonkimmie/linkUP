package dao.home;


import dto.Pay;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

public class PayDAO implements IPayDAO {

    @Override
    public void insertPay(Pay pay) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            sqlSession.insert("mapper.pay.insertPay", pay);
            sqlSession.commit(); // ⭐ commit 반드시
        }
    }
}
