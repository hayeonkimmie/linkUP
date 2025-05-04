package dao.home;


import dto.Pay;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.MybatisSqlSessionFactory;
import util.SingleTonSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayDAO implements IPayDAO {

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

    @Override
    public void insertPay(Pay pay) {
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            sqlSession.insert("mapper.pay.insertPay", pay);
            sqlSession.commit(); // ⭐ commit 반드시
        }
    }

    @Override
    public Pay selectPayByProjectIdandName(Integer projectId, String position) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("position", position);
        Pay pay = null;
        try (SqlSession session = sqlSession.openSession()) {
            pay = session.selectOne("mapper.pay.selectPayByProjectIdandName", param);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pay;
    }

    @Override
    public List<Pay> selectPayByProjectId(Integer projectId) throws Exception {
        List<Pay> payList = null;
        try (SqlSession session = sqlSession.openSession()) {
            payList = session.selectList("mapper.pay.selectPayByProjectId", projectId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return payList;
    }

    @Override
    public List<Pay> getPaysByProjectId(int projectId) {
        List<Pay> payList = null;
        try (SqlSession session = sqlSession.openSession()) {
            payList = session.selectList("mapper.pay.getPaysByProjectId", projectId);
        }
        return payList;
    }

    @Override
    public void deletePaysByProjectId(int projectId) {
        try (SqlSession session = sqlSession.openSession()) {
            session.delete("mapper.pay.deletePaysByProjectId", projectId);
            session.commit();
        }
    }

    @Override
    public Pay selectPayByProjectPayId(Integer projectPayId) throws Exception {
        Pay pay = null;
        try (SqlSession session = sqlSession.openSession()) {
            pay = session.selectOne("mapper.pay.selectPayByProjectPayId", projectPayId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pay;
    }
}
