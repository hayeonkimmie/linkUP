package dao.freelancer;

import dto.*;
import org.apache.ibatis.session.SqlSession;
import service.freelancer.InfoSerializer;
import util.MybatisSqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreelancerDAO implements IFreelancerDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    private InfoSerializer infoSerializer;

    public Freelancer selectBasicFreelancerById(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectBasicFreelancerById", freelancerId);
    }
    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception {
        System.out.println("FreelancerDAO 20 + selectExpertFreelancerById "+freelancerId);
        Freelancer freelancer = sqlSession.selectOne("mapper.freelancer.selectExpertFreelancerById", freelancerId);
        if (freelancer == null) {
            return null;
        } else {
            if(freelancer.getSkill() != null) {
                freelancer.setSkillList(freelancer.getSkill().split(", "));
            } else {
                freelancer.setSkillList(null);
            }
            if(freelancer.getLicense() != null) {
                List<License> licenseList = infoSerializer.deserializeLicenseList(freelancer.getLicense());
                freelancer.setLicenseList(licenseList);
            } else {
                freelancer.setLicenseList(null);
            }
            if (freelancer.getAcademic() != null) {
                List<Academic> academicList = infoSerializer.deserializeAcademicList(freelancer.getAcademic());
                freelancer.setAcademicList(academicList);
            } else {
                freelancer.setAcademicList(null);
            }
            if(freelancer.getExternalUrl() != null) {
                freelancer.setExternalUrlList(freelancer.getExternalUrl().split("\\^"));
            } else{
                freelancer.setExternalUrlList(null);
            }
            if(freelancer.getAttachment() != null) {
                freelancer.setAttachmentList(freelancer.getAttachment().split("\\^"));
            } else{
                freelancer.setAttachmentList(null);
            }
        }
        System.out.println("FreelancerDAO 47 + selectExpertFreelancerById "+freelancer);
        return freelancer;
    }

    public Map<Integer, String> selectAllportfolioInfoMap(String freelancerId) throws Exception {
        return sqlSession.selectMap("mapper.portfolio.selectAllPortfolioInfoForProfile", freelancerId, "portfolioId");
    }
    public List<Portfolio> selectedPortfolioListForProfile(String freelancerId) throws Exception {
        return sqlSession.selectList("mapper.portfolio.selectedPortfolioInfoForProfile", freelancerId);
    }

    public List<Career> selectCareerById(String freelancerId) throws Exception{
        List<Career> careerList = new ArrayList<Career>();
        try {
            careerList = sqlSession.selectList("mapper.freelancer.selectCareerListByFreelancerId", freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return careerList;
    }
    public List<Category> selectCategoryList() {
        List<Category> categoryList = sqlSession.selectList("mapper.subCategory.selectCategoryList");
        System.out.println("DAO 37 + categoryList categoryList"+categoryList);
        return categoryList;
    }

    @Override
    public void insertFreelancer(Freelancer freelancer) throws Exception {

    }
    @Override
    public String selectFreelancerProfileImg(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectProfileImgById", freelancerId);
    }
    @Override
    public void updateUserProfile(Freelancer freelancer) throws Exception {
        System.out.println("DAO 47 + updateUserProfile"+freelancer);
        sqlSession.update("mapper.freelancer.updateUserProfile", freelancer);
        System.out.println("DAO 49 + updateUserProfile "+freelancer);
        sqlSession.update("mapper.freelancer.updateFreelancerProfile", freelancer);
        sqlSession.commit();
    }

    @Override
    public void insertCareer(Career career) throws Exception {
        sqlSession.insert("mapper.freelancer.insertCareer", career);
    }
    @Override
    public void updateCareer(Career career) throws Exception {

    }
    @Override
    public void deleteCareer(String freelancerId) throws Exception {

    }
}
