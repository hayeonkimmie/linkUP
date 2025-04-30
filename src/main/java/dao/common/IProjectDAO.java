package dao.common;

import dto.Project;

import java.util.List;
import java.util.Map;

public interface IProjectDAO {
    List<Project> MainProjectsByCategory(String category);
    List<Project> catalogProjectByCategory(String category);
    List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param);
    List<Project> catalogProjectByConditions(Map<String, String> param);
    void insertProject(Project project); // ✅ 등록만
}
