package service.admin;

import dto.AdminSettleProject;
import dto.PrepareSettleJson;
import dto.Settlelist;

import java.time.LocalDate;
import java.util.HashMap;

public interface ISettlementService {
    Settlelist createSettleList(PrepareSettleJson item, Integer projectId) throws Exception;
    void createSettlement(Settlelist settlelist, PrepareSettleJson[] item, Integer projectId) throws Exception;
    HashMap<Integer, AdminSettleProject> filterProjectsWithUnsettled(HashMap<Integer, AdminSettleProject> fullList) throws Exception;

}
