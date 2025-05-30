package service.client;

import dao.client.IProjectMgtDAO;
import dao.client.ProjectMgtDAOImpl;
import dto.ProjectMgt;

import java.util.List;
import java.util.Map;

public class ProjectMgtServiceImpl implements IProjectMgtService {
    private final IProjectMgtDAO projectMgtDAO; //DAO객체 사용 준비 (실제로 DB에 적용)

    public ProjectMgtServiceImpl() {
        this.projectMgtDAO = new ProjectMgtDAOImpl();
    }

    @Override
    public List<ProjectMgt> getProjectByStatus(Map<String, Object> param) throws Exception {
        return projectMgtDAO.selectProjectMgtByStatus(param);
    }

    @Override
    public void updateStatusToConfirmed(Map<String, Object> param) throws Exception {
        projectMgtDAO.updateStatusToConfirmed(param);
    }

    // DB에 변환을 주기만 하고 , 결과값을 사용하지 않기 때문에 따로 return 필요없음


    @Override
    public void deleteProject(int projectId) throws Exception {
        projectMgtDAO.deleteProject(projectId);
    }

    @Override
    public ProjectMgt getProjectById(int projectId) throws Exception {
        return projectMgtDAO.selectProjectById(projectId);
    }

    @Override
    public void updateProgressToOngoing(Map<String, Object> param) throws Exception {
        projectMgtDAO.updateProgressToOngoing(param);
    }

    @Override
    public void updateProgressToEnd(Map<String, Object> param) throws Exception {
        projectMgtDAO.updateProgressToEnd(param);
    }

    // 상태별 프로젝트 총 개수 조회
    @Override
    public int getProjectCountByStatus(Map<String, Object> param) throws Exception {
        return projectMgtDAO.selectProjectCountByStatus(param);
    }
}

