package service.admin;

import dao.admin.ProjectDAO;
import dto.AdminProject;
import dto.AdminProjectDetail;
import util.PageInfo;

import java.util.List;

public class ProjectService implements IProjectService{

    private static ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        ProjectService.projectDAO = projectDAO;
    }

    @Override
    public AdminProjectDetail selectProjectDetail(Integer projectId) throws Exception{
        return projectDAO.selectProjectDetail(projectId);
    }

    // AdminProjectService.java
    public List<AdminProject> getPagedProjects(int offset, int perPage, String keyword, String settleStatus, String startDate, String endDate) throws Exception {
        return projectDAO.selectPagedProjects(offset, perPage, keyword, settleStatus, startDate, endDate);
    }

    public int getTotalProjectCount(String keyword, String settleStatus, String startDate, String endDate) throws Exception {
        return projectDAO.countProjects(keyword, settleStatus, startDate, endDate);
    }

    public PageInfo calculatePageInfo(int curPage, int perPage, int totalCount) {
        PageInfo pageInfo = new PageInfo(curPage);
        int allPage = (int) Math.ceil((double) totalCount / perPage);
        pageInfo.setAllPage(allPage);
        int startPage = Math.max(1, curPage - 2);
        int endPage = Math.min(allPage, startPage + 4);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        return pageInfo;
    }


}
