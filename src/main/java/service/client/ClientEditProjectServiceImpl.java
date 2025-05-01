package service.client;

import dao.client.IClientEditProjectDAO;
import dao.client.ClientEditProjectDAOImpl;
import dto.Project;

public class ClientEditProjectServiceImpl implements IClientEditProjectService {

    private final IClientEditProjectDAO dao = new ClientEditProjectDAOImpl();

    @Override
    public Project getProjectById(int projectId) throws Exception {
        return dao.selectProjectFixById(projectId);
    }

    @Override
    public void updateProject(Project project) throws Exception {
        dao.updateProject(project);
    }
}
