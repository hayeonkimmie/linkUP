package service;

import dao.admin.DashboardProjectDAO;
import dao.admin.IDashboardProjectDAO;
import dto.DashboardProject;
import dto.Project;
import util.PageInfo;

import java.util.List;

public class ProjectService implements IProjectService {

    private final IDashboardProjectDAO dashboardProjectDAO = new DashboardProjectDAO();

    @Override
    public List<DashboardProject> getDashboardProjectList() {
        return dashboardProjectDAO.selectAllOngoingProjects();
    }

    @Override
    public Integer selectProjectCnt(String userId) {
        return 0;
    }

    @Override
    public List<Project> selectProjectListByPage(PageInfo pageInfo, String userId) throws Exception {
        return null;
    }
}
