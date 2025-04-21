package service.admin;

import dao.admin.ISettlementDAO;
import dto.AdminProject;

import java.util.List;

public class DashboardService implements IDashboardService {

    private final ISettlementDAO settlementDAO;

    public DashboardService(ISettlementDAO settlementDAO) {
        this.settlementDAO = settlementDAO;
    }

    @Override
    public List<AdminProject> getDashboardProjectList() throws Exception {
        return settlementDAO.selectProjectsForSettlement();
    }
}
