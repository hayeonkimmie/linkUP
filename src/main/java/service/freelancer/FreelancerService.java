package service.freelancer;

import dao.freelancer.FreelancerDAO;
import dao.freelancer.IFreelancerDAO;
import dto.*;

import java.util.List;
import java.util.Map;

public class FreelancerService implements IFreelancerService{
    private IFreelancerDAO ifreelancerDAO;
    private InfoSerializer infoSerializer;

    public FreelancerService() {
        super();
        ifreelancerDAO = new FreelancerDAO();
    }
    @Override
    public Freelancer selectBasicFreelancerById(String freelancerId) throws Exception {
        System.out.println("FreelancerService.java 22 freelancerId "+freelancerId);
        Freelancer freelancer = ifreelancerDAO.selectBasicFreelancerById(freelancerId);
        System.out.println("FreelancerService.java 24 freelancer "+freelancer);
        return freelancer;
    }
    @Override
    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception {
        Freelancer freelancer = ifreelancerDAO.selectExpertFreelancerById(freelancerId);
        freelancer.setPortfolioInfoList(selectedPortfolioListForProfile(freelancerId));
        System.out.println("FreelancerService.java 31 freelancer "+freelancer);
        return freelancer;
    }

    @Override
    public Map<Integer, String> selectAllPortfolioInfoMap(String freelancerId) throws Exception {
        return ifreelancerDAO.selectAllportfolioInfoMap(freelancerId);
    }

    @Override
    public List<Portfolio> selectedPortfolioListForProfile(String freelancerId) throws Exception {
        return ifreelancerDAO.selectedPortfolioListForProfile(freelancerId);
    }

    public List<Career> selectCareerListById(String freelancerId) throws Exception {
        return ifreelancerDAO.selectCareerById(freelancerId);
    }

    @Override
    public void updateFreelancer(Freelancer freelancer) throws Exception {
        System.out.println("FreelancerService.java 56 updateFreelancer "+freelancer);
        String curProfileImg = ifreelancerDAO.selectFreelancerProfileImg(freelancer.getFreelancerId());
        if(freelancer.getProfileImg() == null || freelancer.getProfileImg().equals(curProfileImg)) {
            freelancer.setProfileImg(curProfileImg);
        }
        try {
            ifreelancerDAO.updateUserProfile(freelancer);
            ifreelancerDAO.updateFreelancer(freelancer);
        }catch (Exception e) {
            throw new Exception();
        }
        System.out.println("FreelancerService.java 56 + updateFreelancer 동작완료");
    }
    @Override
    public void updateCareer(List<Career> careerList, String freelancerId) throws Exception {
        System.out.println("FreelancerService.java 56 updateCareer "+careerList);
        try {
            ifreelancerDAO.deleteCareer(freelancerId);
            ifreelancerDAO.insertCareer(careerList);
        }catch (Exception e){
            throw new Exception();
        }
    }
}
