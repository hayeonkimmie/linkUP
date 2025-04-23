package dao.admin;

import dto.AdminPrepareSettle;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;

public class ContractDAO implements IContractDAO{

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public HashMap<String, AdminPrepareSettle> selectInfoForSettle(String id) throws Exception {
        HashMap<String, AdminPrepareSettle> prepareSettleMap = new HashMap<>();
//        List<AdminPrepareSettle> prepareSettleList = sqlSession.selectList("mapper.contract.selectInfoForSettle", id);
//        for(AdminPrepareSettle prepareSettle : prepareSettleList) {
//            prepareSettleMap.put(prepareSettle.getId(), prepareSettle);
//        }
        return prepareSettleMap;
    }

    @Override
    public AdminPrepareSettle selectInfoForSettleById(String id) throws Exception {
        return sqlSession.selectOne("mapper.contract.selectInfoForSettleById", id);
    }
}
