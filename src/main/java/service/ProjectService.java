package service;

import dao.admin.DashboardProjectDAO;
import dao.admin.IDashboardProjectDAO;
import dto.DashboardProject;
import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public class ProjectService implements IProjectService {

    private final IDashboardProjectDAO dashboardProjectDAO = new DashboardProjectDAO();

    @Override
    public List<DashboardProject> getDashboardProjectList() {
        return dashboardProjectDAO.selectAllOngoingProjects();
    }

    @Override
    public Integer selectProjectCnt(String user_id) {
        return 0;
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception {
        return null;
    }
}
