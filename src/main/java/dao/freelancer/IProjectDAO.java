package dao.freelancer;

import dto.FreelancerProject;

import java.util.List;

public interface IProjectDAO {
    List<FreelancerProject> selectOngoingProject(Integer row, String freelancerId) throws Exception;
    List<FreelancerProject> selectCompletedProject(Integer row, String freelancerId) throws Exception;

    int cntOngoingProjects(String freelancerId) throws  Exception;
    int cntCompletedProjects(String freelancerId) throws  Exception;
}
