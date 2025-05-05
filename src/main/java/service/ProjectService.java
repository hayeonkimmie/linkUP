package service;

import dao.admin.DashboardProjectDAO;
import dao.admin.FreelancerDAO;
import dao.admin.IDashboardProjectDAO;
import dao.common.IProjectDAO;
import dao.common.ProjectDAO;
import dao.home.ApplyDAO;
import dao.home.IPayDAO;
import dao.home.PayDAO;
import dto.*;
import util.PageInfo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ProjectService implements IProjectService {

    private final IDashboardProjectDAO dashboardProjectDAO = new DashboardProjectDAO();
    private final IProjectDAO projectDAO = new ProjectDAO();
    private final IPayDAO payDAO = new PayDAO();
    private final FreelancerDAO freelancerDAO = new FreelancerDAO();

    @Override
    public List<DashboardProject> getDashboardProjectList() {
        return dashboardProjectDAO.selectAllOngoingProjects();
    }

    @Override
    public Integer selectProjectCnt(String userId) {
        return 0;
    }

    @Override
    public List<Project> selectProjectListByPage(PageInfo pageInfo, String userId) throws Exception {
        return null;
    }
    @Override
    public List<Project> MainProjectsByCategory(String category) {
        return projectDAO.MainProjectsByCategory(category);
    }
    @Override
    public List<Project> catalogProjectByCategory(String category) {
        return projectDAO.catalogProjectByCategory(category);
    }
    @Override
    public List<Project> searchProjectsByCategoryAndKeyword(Map<String, String> param) {
        return projectDAO.searchProjectsByCategoryAndKeyword(param);
    }
    @Override
    public List<Project> catalogProjectByConditions(Map<String, String> param) {
        return projectDAO.catalogProjectByConditions(param);
    }

    @Override
    public ProjectDetail selectProjectById(Integer projectId) throws Exception {
        return projectDAO.selectProjectById(projectId);
    }

    @Override
    public Apply createApply(Integer projectId, String freelancerId, String position) throws Exception {
        ProjectDetail project = projectDAO.selectProjectById(projectId);
        if (project == null) {
            throw new Exception("프로젝트를 찾을 수 없습니다.");
        }
        Pay pay = payDAO.selectPayByProjectIdandName(projectId, position);
        if (pay == null) {
            throw new Exception("포지션 정보를 찾을 수 없습니다.");
        }
        AdminFreelancer freelancer = freelancerDAO.selectFreelancerById(freelancerId);
        if (freelancer == null) {
            throw new Exception("프리랜서를 찾을 수 없습니다.");
        }
        System.out.println("position : " + pay);
        System.out.println("Freelancer : " + freelancer);
        System.out.println("Project : " + project);
        Apply apply = new Apply();
        apply.setFreelancerId(freelancerId);
        apply.setProjectId(projectId);
        apply.setApplyDate(new Date(System.currentTimeMillis()));
        apply.setCancelDate(null);
        apply.setIsApproved(false);
        apply.setProjectPayId(pay.getProjectPayId());
        apply.setSubCategoryName(pay.getCategoryName());

        ApplyDAO applyDAO = new ApplyDAO();
        applyDAO.makeProjectApply(apply);
        return null;
    }
    public boolean isProjectLiked(String freelancerId, Integer projectId) throws Exception {
       return projectDAO.isProjectLiked(freelancerId, projectId);
    }
    public Integer likeProject (String freelancerId, Integer projectId) throws Exception {
        System.out.println("서비스 도착 likeProject freelancerId = " + freelancerId + ", projectId = " + projectId);
        return projectDAO.likeProject(freelancerId, projectId);
    }
    public void cancelProjectlike (String freelancerId, Integer projectId) {
        projectDAO.cancelProjectLike(freelancerId, projectId);
    }
}
