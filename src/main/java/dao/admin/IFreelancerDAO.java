package dao.admin;

import dto.Freelancer;

import java.util.List;

public interface IFreelancerDAO {
    List<Freelancer> selectAllFreelancer() throws Exception;
}
