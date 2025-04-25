package service;

import dao.admin.DashboardProjectDAO;
import dao.admin.IDashboardProjectDAO;
import dao.common.IProjectDAO;
import dao.common.ProjectDAO;
import dto.DashboardProject;
import dto.Project;
import util.PageInfo;

import java.util.List;
import java.util.Map;

public class ProjectService implements IProjectService {

    private final IDashboardProjectDAO dashboardProjectDAO = new DashboardProjectDAO();
    private final IProjectDAO projectDAO = new ProjectDAO();
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
    @Override
    public List<Project> MainProjectsByCategory(String category) {
        return projectDAO.MainProjectsByCategory(category);
    }
    @Override
    public List<Project> catalogProjectByCategory(String category) {
        return projectDAO.catalogProjectByCategory(category);
    }
    @Override
    public List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param) {
        return projectDAO.searchProjectsByCategoryAndKeyword(param);
    }
    @Override
    public List<Project> catalogProjectByConditions(Map<String, String> param) {
        return projectDAO.catalogProjectByConditions(param);
    }
}
