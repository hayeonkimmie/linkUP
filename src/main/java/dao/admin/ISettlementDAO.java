package dao.admin;

import dto.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ISettlementDAO {
    HashMap<Integer, AdminSettleProject> selectProjectsForSettlement() throws SQLException;

    List<AdminProject> selectProjectsForSettlementList() throws SQLException;

    Integer selectNextSettlementCount(Integer projectId) throws SQLException;

    List<AdminSettleTarget> selectFreelancersForSettlement(Integer projectId, Integer cnt) throws SQLException;

    void createSettlelist(Settlelist settlelist) throws SQLException;

    void insertSettlement(Settlement settlement) throws SQLException;

    Integer getMaxCntByProjectId(Integer projectId) throws SQLException;

    Date selectLatestSettleDateByProjectId(Integer contractId) throws SQLException;

    Settlelist selectSettlelistByDateAndProject(int projectId, Date settleDate) throws SQLException;

    Settlelist selectSettlelistByProjectIdAndDate(int projectId, Date settleDate);

    boolean existsSettlementBySlistIdAndsettleDate(String clientId, int slistId, Date settleDate) throws Exception;

    Settlelist selectAnySettlelistByProjectIdAndDate(int projectId, Date settleDate) throws Exception;



}
