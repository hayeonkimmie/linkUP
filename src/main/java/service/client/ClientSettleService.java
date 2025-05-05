package service.client;

import dao.client.ClientSettlementDAO;
import dao.client.IClientSettlementDAO;

import java.util.List;

public class ClientSettleService implements IClientSettleService {

    private final IClientSettlementDAO clientSettlementDAO = new ClientSettlementDAO();
    @Override
    public int getNextSettleRound(Integer projectId) throws Exception {
        int contractCount = clientSettlementDAO.countContractsByProject(projectId);

        // settlement 테이블에서 사용된 cnt (회차) 리스트 가져오기 (내림차순)
        List<Integer> usedRounds = clientSettlementDAO.getUsedSettleRounds(projectId);  // e.g., [2, 1]

        for (Integer cnt : usedRounds) {
            int settledCount = clientSettlementDAO.countSettlementsByProjectAndRound(projectId, cnt);
            System.out.println(settledCount + " / " + contractCount);
            System.out.println("contract : " + contractCount);
            if (settledCount < contractCount) {
                return cnt; // 해당 회차 정산 미완료 → 그 회차를 리턴
            }
        }

        // 모두 정산된 경우 → 마지막 회차 + 1
        if (!usedRounds.isEmpty()) {
            return usedRounds.get(0) + 1;
        }

        return 1; // 아무 정산도 없었으면 1회차부터
    }


}
