package service.freelancer;

import dto.FreelancerProject;
import util.PageInfo;

import java.util.List;

public interface IFreelancerProjectService {
    public List<FreelancerProject> selectCompletedProject(PageInfo pageInfo, String freelancerId) throws Exception;
    public List<FreelancerProject> selectOngoingProject(PageInfo pageInfo, String freelancerId) throws Exception;
    public Integer cntOngoingProjects(String freelancerId) throws Exception;
    public Integer cntCompletedProjects(String freelancerId) throws Exception;
}
