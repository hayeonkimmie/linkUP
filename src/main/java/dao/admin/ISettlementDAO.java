package dao.admin;

import dto.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ISettlementDAO {
    HashMap<Integer, AdminProject> selectProjectsForSettlement() throws SQLException;

    List<AdminProject> selectProjectsForSettlementList() throws SQLException;

    Integer selectNextSettlementCount(Integer projectId) throws SQLException;

    List<AdminSettleTarget> selectFreelancersForSettlement(Integer projectId, Integer cnt) throws SQLException;

    void createSettlelist(Settlelist settlelist) throws SQLException;

    void insertSettlement(Settlement settlement) throws SQLException;

    Integer getMaxCntByContractId(String id) throws SQLException;
}
