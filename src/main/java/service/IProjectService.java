package service;
import dto.Portfolio;
import util.PageInfo;

import java.util.List;

import dto.DashboardProject;

import java.util.List;

public interface IProjectService {
    /**
     * 대시보드에 보여줄 프로젝트 정보 가져오기
     *
     * @return List<DashboardProject> 대시보드에 보여줄 프로젝트 정보
     */
    public List<DashboardProject> getDashboardProjectList();

    Integer selectProjectCnt(String user_id);
    List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception;

}
