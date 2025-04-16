package service;

import dao.admin.DashboardProjectDAO;
import dao.admin.IDashboardProjectDAO;
import dto.DashboardProject;

import java.util.List;

public class ProjectService implements IProjectService {

    private final IDashboardProjectDAO dashboardProjectDAO = new DashboardProjectDAO();

    @Override
    public List<DashboardProject> getDashboardProjectList() {
        return dashboardProjectDAO.selectAllOngoingProjects();
    }
}
