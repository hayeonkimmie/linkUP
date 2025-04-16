package service;

import dto.DashboardProject;

import java.util.List;

public interface IProjectService {
    /**
     * 대시보드에 보여줄 프로젝트 정보 가져오기
     *
     * @return List<DashboardProject> 대시보드에 보여줄 프로젝트 정보
     */
    public List<DashboardProject> getDashboardProjectList();


}
