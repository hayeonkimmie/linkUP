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

    // DB에 변환을 주기만 하고 , 결과값을 사용하지 않기 때문에 따로 return 필요없음
    @Override
    public void updateProjectStatusToConfirmed(int projectId) throws Exception {
        projectMgtDAO.updateStatusToConfirmed(projectId);

    }

    @Override
    public void deleteProject(int projectId) throws Exception {
        projectMgtDAO.deleteProject(projectId);
    }
}

