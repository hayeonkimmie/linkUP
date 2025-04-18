package service;
import dto.Project;
import util.PageInfo;

import java.util.List;

import dto.DashboardProject;

import java.util.List;

public interface IProjectService {
    List<DashboardProject> getDashboardProjectList() throws Exception;
    Integer selectProjectCnt(String userId) throws Exception;
    List<Project> selectPortfolioListByPage(PageInfo pageInfo, String userId) throws Exception;

}
