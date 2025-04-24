package service.client;

import dto.ProjectMgt;

import java.util.List;
import java.util.Map;

public interface IProjectMgtService {
    // 구인상태 (구인중, 시작전, 진행중, 완료)
    List<ProjectMgt> getProjectByStatus(Map<String, Object> param) throws Exception;// 상태에 따른 내 프로젝트 조회
    void updateProjectStatusToConfirmed(int projectId) throws Exception; //구인상태 확정 버튼

    void deleteProject(int projectId) throws Exception;
}
