package dao.client;

import dto.ProjectMgt;

import java.util.List;
import java.util.Map;

public interface IProjectMgtDAO {
    List<ProjectMgt> selectProjectMgtByStatus(Map<String, Object> param) throws Exception;
    void updateStatusToConfirmed(int projectId) throws Exception;

    void deleteProject(int projectId) throws Exception;
}
