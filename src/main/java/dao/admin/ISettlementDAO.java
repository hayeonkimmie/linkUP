package dao.admin;

import dto.AdminProject;
import java.sql.SQLException;
import java.util.List;

public interface ISettlementDAO {
    List<AdminProject> selectProjectsForSettlement() throws SQLException;
}
