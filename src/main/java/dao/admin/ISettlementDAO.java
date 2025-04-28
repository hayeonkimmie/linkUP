package dao.admin;

import dto.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISettlementDAO {
    HashMap<Integer, AdminSettleProject> selectProjectsForSettlement() throws SQLException;

    List<AdminProject> selectProjectsForSettlementList() throws SQLException;

    Integer selectNextSettlementCount(Integer projectId) throws SQLException;

    List<AdminSettleTarget> selectFreelancersForSettlement(Integer projectId, Date startDate, Date endDate) throws SQLException ;

    void createSettlelist(Settlelist settlelist) throws SQLException;

    void insertSettlement(Settlement settlement) throws SQLException;

    Integer getMaxCntByProjectId(Integer projectId) throws SQLException;

    Date selectLatestSettleDateByProjectId(Integer contractId) throws SQLException;

    Settlelist selectSettlelistByDateAndProject(int projectId, Date settleDate) throws SQLException;

    Settlelist selectSettlelistByProjectIdAndDate(int projectId, Date settleDate);

    public boolean existsSettlementBySlistIdAndStartEndDate(int slistId, String startDate, String endDate, String freelancerName) throws Exception;

    Settlelist selectAnySettlelistByProjectIdAndDate(int projectId, Date settleDate) throws Exception;

    List<AdminSettleHistory> selectHistoryList(Map<String, Object> param) throws Exception;

    Integer countHistory(Map<String, Object> param) throws Exception;


    List<AdminSettleHistorySummary> selectHistorySummaryList(Map<String, Object> param);

    int countHistorySummary(Map<String, Object> param);

    HashMap<Integer ,AdminSettleHistory> selectSettlementHistoryDetail(Integer projectId);

    List<SettledInfoDTO> selectSettledFreelancers(Integer slistId) throws Exception;

    List<SettledInfoDTO> selectWaitingFreelancers(Integer projectId, Integer slistId) throws Exception;

    Map<String, Date> selectSettleStartandEnd(Integer projectId) throws Exception;

    boolean isAllSettledInCnt(Integer projectId, Integer cnt) throws Exception;

    List<Map<String, Object>> selectAllSettlementMonthsByProjectId(Integer projectId) throws Exception;
}
