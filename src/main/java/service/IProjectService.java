package service;
import dto.Project;
import util.PageInfo;

import java.util.List;

import dto.DashboardProject;

import java.util.List;

public interface IProjectService {
    Integer selectProjectCnt(String userId);
    List<Project> selectPortfolioListByPage(PageInfo pageInfo, String userId) throws Exception;

}
