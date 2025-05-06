package service.freelancer;

import dto.Career;
import dto.Freelancer;
import dto.Portfolio;
import dto.User;

import java.util.List;
import java.util.Map;

public interface IFreelancerService {
    Freelancer selectBasicFreelancerById(String freelancerId) throws Exception;

    boolean registerFreelancer(User user, Freelancer freelancer);

    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception;
//    void insertCareer(Career career) throws Exception;
    void updateFreelancer(Freelancer freelancer) throws Exception;
//    void updateCareer(Career career) throws Exception;
//    void updateCareer(List<Career> careerList) throws Exception;
    List<Career> selectCareerListById (String freelancerId) throws Exception;
    Map<Integer, String> selectAllPortfolioInfoMap(String userId) throws Exception;
    List<Portfolio> selectedPortfolioListForProfile(String freelancerId) throws Exception;
    void updateCareer(List<Career> careerList, String freelancerId) throws Exception;
}
