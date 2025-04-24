package service.admin;

import dto.PrepareSettleJson;
import dto.Settlelist;

import java.time.LocalDate;

public interface ISettlementService {
    Settlelist createSettleList(PrepareSettleJson item, Integer projectId) throws Exception;
    void createSettlement(Settlelist settlelist, PrepareSettleJson[] item, Integer projectId) throws Exception;
}
