package service.admin;

import dto.*;
import util.PageInfo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ISettlementService {
    Settlelist createSettleList(PrepareSettleJson item, Integer projectId) throws Exception;
    Settlement createSettlement(Settlelist settlelist, PrepareSettleJson[] item, Integer projectId) throws Exception;
//    HashMap<Integer, AdminSettleProject> filterProjectsWithUnsettled(HashMap<Integer, AdminSettleProject> fullList) throws Exception;

    HashMap<Integer, AdminSettleProject> filterProjectsWithUnsettled() throws Exception;

    List<AdminSettleHistory> getHistoryList(String keyword, String startDate, String endDate, int offset, int limit) throws Exception;

    int countHistory(String keyword, String startDate, String endDate) throws Exception;

    List<AdminSettleHistorySummary> selectHistorySummaryList(String keyword, String startDate, String endDate, int offset, int limit) throws Exception;

    int countHistorySummary(String keyword, String startDate, String endDate) throws Exception;
}
