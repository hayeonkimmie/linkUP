package service.freelancer;

import dto.SettlementListForF;
import java.util.List;

public interface SettlementMapper {
    List<SettlementListForF> selectSettlementList(String freelancerId, Integer projectId);
}