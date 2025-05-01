package service.admin;

import dao.admin.IFreelancerDAO;
import dao.freelancer.CareerDAO;
import dto.AdminFreelancer;
import dto.Career;
import dto.Freelancer;

import java.util.List;

public class FreelancerService implements IFreelancerService {

    private final IFreelancerDAO freelancerDAO;
    CareerDAO careerDAO = new CareerDAO();

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

    public List<Career> selectCareerListByFreelancerId(String freelancerId) throws Exception {
        System.out.println("FreelancerId : " + freelancerId);
        List<Career> careerList = careerDAO.selectCareerListByFreelancerId(freelancerId);
        for (Career career : careerList) {
            System.out.println("Career : " + career);
        }
        return careerList;
    }

}
