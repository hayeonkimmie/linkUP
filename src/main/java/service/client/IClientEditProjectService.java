package service.client;

import dto.Project;

public interface IClientEditProjectService {
    Project getProjectById(int projectId) throws Exception;

    void updateProject(Project project) throws Exception;
}
