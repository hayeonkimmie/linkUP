package dao.client;

import dto.Project;

public interface IClientEditProjectDAO {
    Project selectProjectFixById(int projectId);
    void updateProject(Project project);
}
