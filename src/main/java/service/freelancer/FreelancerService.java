package service.freelancer;

import dao.freelancer.FreelancerDAO;
import dao.freelancer.IFreelancerDAO;
import dto.Academic;
import dto.Career;
import dto.Freelancer;
import dto.License;

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
        freelancer.setSkillList(freelancer.getSkill().split("\\^"));
        freelancer.setPortfolioInfoMap(ifreelancerDAO.selectAllportfolioInfoMap(freelancerId));
        List<Academic> academicList = infoSerializer.deserializeAcademicList(freelancer.getAcademic());
        freelancer.setAcademicList(academicList);
        List<License> licenseList = infoSerializer.deserializeLicenseList(freelancer.getLicense());
        freelancer.setLicenseList(licenseList);
        return freelancer;
    }

    @Override
    public Map<Integer, String> selectAllportfolioInfoMap(String freelancerId) throws Exception {
        return ifreelancerDAO.selectAllportfolioInfoMap(freelancerId);
    }

    @Override
    public Career insertCareer(Career career) throws Exception {
        return ifreelancerDAO.insertCareer(career);
    }
    public List<Career> selectCareerListById(String freelancerId) throws Exception {
        return ifreelancerDAO.selectCareerById(freelancerId);
    }

    @Override
    public void updateFreelancer(Freelancer freelancer) throws Exception {
        ifreelancerDAO.modifyFreelancer(freelancer);
        System.out.println("FreelancerService.java 56 + updateFreelancer");
    }

    @Override
    public void updateCareer(Career career) throws Exception {

    }

    @Override
    public void deleteCareer(String userId) throws Exception {

    }
}
