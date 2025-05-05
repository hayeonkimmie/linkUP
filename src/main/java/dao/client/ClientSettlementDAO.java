package dao.client;

import dto.ClientSettleTarget;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SingleTonSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientSettlementDAO implements IClientSettlementDAO {

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

    @Override
    public List<ClientSettleTarget> selectSettleTargetsByClient(String clientId, Integer projectId) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientId);
        params.put("projectId", projectId);

        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectList("mapper.settlelist.selectSettleTargetsByClient", params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }

    @Override
    public Integer getLatestSettleRound(Integer projectId) throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.settlelist.getLatestSettleRound", projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer countSettlementsByProjectAndRound(Integer projectId, Integer cnt) throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
            params.put("cnt", cnt);
            return session.selectOne("mapper.settlelist.countSettlementsByProjectAndRound", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer countContractsByProject(Integer projectId) throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.settlelist.countContractsByProject", projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Integer> getUsedSettleRounds(Integer projectId) {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectList("mapper.settlelist.getUsedSettleRounds", projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
