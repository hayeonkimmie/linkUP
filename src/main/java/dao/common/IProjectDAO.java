package dao.common;

import dto.Project;
import dto.ProjectDetail;

import java.util.List;
import java.util.Map;

public interface IProjectDAO {
    List<Project> MainProjectsByCategory(String category);
    List<Project> catalogProjectByCategory(String category);
    List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param);
    List<Project> catalogProjectByConditions(Map<String, String> param);
    ProjectDetail selectProjectById(Integer projectId) throws Exception;
    void insertProject(Project project); // ✅ 등록만
    List<Project> getProjectById(int projectId) throws Exception; // 프로젝트 수정 기능

}
