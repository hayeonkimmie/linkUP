package dao.client;

import dto.ProjectMgt;

import java.util.List;
import java.util.Map;

public interface IProjectMgtDAO {
    List<ProjectMgt> selectProjectMgtByStatus(Map<String, Object> param) throws Exception;
    void updateStatusToConfirmed(Map<String, Object> param) throws Exception;

    void deleteProject(int projectId) throws Exception;
// 'today'라는 이름으로 파라미터 넘기기 위해서 Key(today) 지정을 위해 Map사용
    // 실제로 넘겨줄 값 : new Date()
    void updateProgressToOngoing(Map<String, Object> param) throws Exception;
    void updateProgressToEnd(Map<String, Object> param) throws Exception;


    ProjectMgt selectProjectById(int projectId) throws Exception;

    int selectProjectCountByStatus(Map<String, Object> param) throws Exception;
}
