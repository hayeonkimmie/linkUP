package service;
import dto.Project;
import dto.ProjectDetail;
import util.PageInfo;

import java.util.List;

import dto.DashboardProject;

import java.util.Map;

public interface IProjectService {
    List<DashboardProject> getDashboardProjectList() throws Exception;
    Integer selectProjectCnt(String userId) throws Exception;
    List<Project> selectProjectListByPage(PageInfo pageInfo, String userId) throws Exception;
    List<Project> MainProjectsByCategory(String category);
    List<Project> catalogProjectByCategory(String category);
    List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param);
    List<Project> catalogProjectByConditions(Map<String, String> param);
    ProjectDetail selectProjectById(Integer projectId) throws Exception;
}
