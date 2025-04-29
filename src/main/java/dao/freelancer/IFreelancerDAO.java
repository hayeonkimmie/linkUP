package dao.freelancer;
import dto.Freelancer;
import dto.Career;
import dto.Portfolio;

import java.util.List;
import java.util.Map;

public interface IFreelancerDAO {
    public Freelancer selectBasicFreelancerById(String freelancerId) throws Exception;
    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception;
    public Map<Integer, String> selectAllportfolioInfoMap(String freelancerId) throws Exception;
    public List<Career> selectCareerById(String freelancerId) throws Exception;
    public List<Portfolio> selectedPortfolioListForProfile(String freelancerId) throws Exception;
//    public void insertCareer(Career career) throws Exception;
    void insertCareer(List<Career> careerList) throws Exception;
    public void deleteCareer(String freelancerId) throws Exception;
    public void updateUserProfile(Freelancer freelancer) throws Exception;
    public String selectFreelancerProfileImg(String freelancerId) throws Exception;
    void updateCareer(List<Career> careerList, String freelancerId) throws Exception;
    void updateFreelancer(Freelancer freelancer) throws Exception;

}
