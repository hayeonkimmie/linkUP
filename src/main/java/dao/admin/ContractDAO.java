package dao.admin;

import dto.AdminPrepareSettle;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SingleTonSession;

import java.util.HashMap;
import java.util.Map;

public class ContractDAO implements IContractDAO{

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

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
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.contract.selectInfoForSettleById", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }

    @Override
    public Integer countContractsByProjectId(int projectId) {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.contract.countContractsByProjectId", projectId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }


    @Override
    public void updateClientStatus(int projectId, String clientStatus) throws Exception {
        try (SqlSession session = sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("clientStatus", clientStatus);
            session.update("mapper.contract.updateClientStatus", param);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("구인자 정산 상태 업데이트 중 오류 발생", e);
        }
    }
}
