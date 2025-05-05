package service;
import dto.Apply;
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
    Apply createApply(Integer projectId, String freelancerId, String position) throws Exception;

    boolean isProjectLiked(String freelancerId, Integer projectId) throws Exception;
    Integer likeProject(String freelancerId, Integer projectId) throws Exception;
    void cancelProjectlike(String freelancerId, Integer projectId) throws Exception;
}
