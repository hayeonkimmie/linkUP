package service.client;

import dto.ProjectMgt;

import java.util.List;
import java.util.Map;

public interface IProjectMgtService {
    // 구인상태 (전체, 구인중)
    List<ProjectMgt> getProjectByStatus(Map<String, Object> param) throws Exception;// 상태에 따른 내 프로젝트 조회
    void updateStatusToConfirmed(Map<String, Object> param) throws Exception; //구인상태 확정 버튼 (구인 중 -> 시작 전)
    void deleteProject(int projectId) throws Exception; // 현재 구인 중인 프로젝트 삭제

    //프로젝트 진행상태 변경 (시작전 -> 진행중)
    void updateProgressToOngoing(Map<String, Object> param) throws Exception;

    //프로젝트 진행상태 변경 (진행중 -> 종료됨)
    void updateProgressToEnd(Map<String, Object> param) throws Exception;
}
