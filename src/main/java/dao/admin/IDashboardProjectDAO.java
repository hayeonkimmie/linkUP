package dao.admin;

import dto.DashboardProject;

import java.util.List;

public interface IDashboardProjectDAO {
    /**
     * 대시보드에 보여줄 프로젝트 정보 가져오기
     * @return List<Project> 대시보드에 보여줄 프로젝트 정보
     */
    public List<DashboardProject> selectAllOngoingProjects();

    /**
     * 대시보드에 보여줄 프로젝트 정보 가져오기
     * @param projectId 프로젝트 ID
     * @return Project 대시보드에 보여줄 프로젝트 정보
     */
    public DashboardProject getDashboardProject(Integer projectId);
}
