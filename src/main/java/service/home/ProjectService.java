package service.home;

import dao.common.IProjectDAO;
import dao.common.ProjectDAO;
import dto.Project;

public class ProjectService implements IProjectService{
    private final IProjectDAO projectDAO = new ProjectDAO();

    @Override
    public void registerProject(Project project) {
        projectDAO.insertProject(project);
    }
}
