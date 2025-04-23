package dao.freelancer;
import dto.Freelancer;
import dto.Career;

import java.util.List;
import java.util.Map;

public interface IFreelancerDAO {
    public Freelancer selectBasicFreelancerById(String freelancerId) throws Exception;
    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception;
    public Map<Integer, String> selectAllportfolioInfoMap(String freelancerId) throws Exception;
    public List<Career> selectCareerById(String freelancerId) throws Exception;
    public void insertFreelancer(Freelancer freelancer) throws Exception;
    public void insertCareer(Career career) throws Exception;
    public void updateCareer(Career career) throws Exception;
    public void deleteCareer(String freelancerId) throws Exception;
    public void updateUserProfile(Freelancer freelancer) throws Exception;
    public List<Integer> selectCategoryIdByFreelancerId(String freelancerId) throws Exception;
}
