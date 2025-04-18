package service;

import dto.DashboardProject;
import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public class ProjectService implements IProjectService {


    @Override
    public List<DashboardProject> getDashboardProjectList() {
        return List.of();
    }

    @Override
    public Integer selectProjectCnt(String user_id) {
        return 0;
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception {
        return List.of();
    }

    @Override
    public Integer selectProjectCnt(String user_id) {
        return 0;
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception {
        return null;
    }
}
