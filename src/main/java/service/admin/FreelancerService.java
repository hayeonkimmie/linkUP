package service.admin;

import dao.admin.IFreelancerDAO;
import dto.AdminFreelancer;
import dto.Freelancer;

import java.util.List;

public class FreelancerService implements IFreelancerService {

    private final IFreelancerDAO freelancerDAO;

    public FreelancerService(IFreelancerDAO freelancerDAO) {
        this.freelancerDAO = freelancerDAO;
    }



    @Override
    public List<Freelancer> selectAllFreelancer() throws Exception {
        return freelancerDAO.selectAllFreelancer();
    }

    @Override
    public List<Freelancer> searchFreelancersByKeyword(String keyword) throws Exception {
        return freelancerDAO.searchFreelancersByKeyword(keyword);
    }

    @Override
    public AdminFreelancer selectFreelancerById(String freelancerId) throws Exception {
        return freelancerDAO.selectFreelancerById(freelancerId);
    }


}
