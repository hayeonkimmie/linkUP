package dao.admin;

import dto.AdminFreelancer;
import dto.Freelancer;

import java.util.List;

public interface IFreelancerDAO {
    List<Freelancer> selectAllFreelancer() throws Exception;
    List<Freelancer> searchFreelancersByKeyword(String keyword) throws Exception;
    AdminFreelancer selectFreelancerById(String freelancerId) throws Exception;
}
