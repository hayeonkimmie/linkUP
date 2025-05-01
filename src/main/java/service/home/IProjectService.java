package service.home;

import dto.Project;
import dto.ProjectDetail;

public interface IProjectService {
    void registerProject(Project project); // 프로젝트 등록
    ProjectDetail getProjectById(int projectId) throws Exception; //프로젝트 수정
}
