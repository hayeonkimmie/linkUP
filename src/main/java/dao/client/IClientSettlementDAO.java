package dao.client;

import dto.ClientSettleTarget;

import java.util.List;

public interface IClientSettlementDAO {
    List<ClientSettleTarget> selectSettleTargetsByClient(String clientId, Integer projectId) throws Exception;
    Integer getLatestSettleRound(Integer projectId) throws Exception;
    Integer countSettlementsByProjectAndRound(Integer projectId, Integer cnt) throws Exception;
    Integer countContractsByProject(Integer projectId) throws Exception;

    List<Integer> getUsedSettleRounds(Integer projectId);
}
