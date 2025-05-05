package dao.freelancer;

import dto.*;
import org.apache.ibatis.session.SqlSession;
import service.freelancer.InfoSerializer;
import util.MybatisSqlSessionFactory;

import java.util.*;

public class FreelancerDAO implements IFreelancerDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    private InfoSerializer infoSerializer;

    public Freelancer selectBasicFreelancerById(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectBasicFreelancerById", freelancerId);
    }
    public Freelancer selectExpertFreelancerById(String freelancerId) throws Exception {
        System.out.println("FreelancerDAO 18 + selectExpertFreelancerById "+freelancerId);
        Freelancer freelancer = sqlSession.selectOne("mapper.freelancer.selectExpertFreelancerById", freelancerId);
        if (freelancer == null) {
            return null;
        } else {
            if(freelancer.getSkill() != null) {
                freelancer.setSkillList(freelancer.getSkill().split("\\^"));
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
                freelancer.setExternalUrlList(Arrays.asList(freelancer.getExternalUrl().split("\\^")));
            } else{
                freelancer.setExternalUrlList(null);
            }
            if(freelancer.getAttachment() != null) {
                freelancer.setAttachmentList(Arrays.asList(freelancer.getAttachment().split("\\^")));
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
        System.out.println("DAO 75 + categoryList categoryList"+categoryList);
        return categoryList;
    }
    @Override
    public String selectFreelancerProfileImg(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.freelancer.selectProfileImgById", freelancerId);
    }
    @Override
    public void updateUserProfile(Freelancer freelancer) throws Exception {
        System.out.println("DAO 84 + updateUserProfile"+freelancer);
        try {
            sqlSession.update("mapper.freelancer.updateUserProfile", freelancer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("DAO 86 + updateUserProfile "+freelancer);
        Map <String,Object> param =new HashMap<>();
        param.put("bank",freelancer.getBank());
        param.put("accountNum",freelancer.getAccountNum());
        param.put("address",freelancer.getAddress());
        param.put("freelancerId",freelancer.getFreelancerId());
        System.out.println("DAO 96 + param "+param);
        try {
            sqlSession.update("mapper.freelancer.updateFreelancerInfo", param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sqlSession.commit();
    }

    @Override
    public void updateCareer(List<Career> careerList, String freelancerId) throws Exception {
        System.out.println("FreelancerDAO 98 updateCareer freelancerId :"+freelancerId);
        deleteCareer(freelancerId);
        System.out.println("FreelancerDAO 100 updateCareer "+careerList);
        insertCareer(careerList);
    }

    @Override
    public void updateFreelancer(Freelancer freelancer) throws Exception {
            if(freelancer.getLicenseList() != null) {
                String license = infoSerializer.serializeLicenseList(freelancer.getLicenseList());
                System.out.println("FreelancerDAO 109 license : "+license);
                freelancer.setLicense(license);
            } else {
                freelancer.setLicense(null);
            }
            if (freelancer.getAcademicList() != null) {
                String academic = infoSerializer.serializeAcademicList(freelancer.getAcademicList());
                System.out.println("FreelancerDAO 116 academic : "+academic);
                freelancer.setAcademic(academic);
            } else {
                freelancer.setAcademic(null);
            }
            System.out.println("FreelancerDAO 121 "+freelancer);
        try {
            sqlSession.update("mapper.freelancer.updateFreelancer", freelancer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sqlSession.commit();
    }

    @Override
    public void insertCareer(List<Career> careerList) throws Exception {

        try {
            for (Career c : careerList) {
                sqlSession.insert("mapper.freelancer.insertCareer", c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sqlSession.commit();
    }
    @Override
    public void deleteCareer(String freelancerId) throws Exception {

        try {
            sqlSession.delete("mapper.freelancer.deleteCareerByFreelancerId", freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sqlSession.commit();
    }

/*    @Override
    public boolean isFreelancerLiked(String freelancerId, String clientId) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("freelancerId", freelancerId);
            param.put("clientId", clientId);
            Integer cnt = sqlSession.selectOne("mapper.jjimfree.isFreelancerLiked", param);
            return cnt == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer likeFreelancer(String freelancerId, String clientId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("freelancerId", freelancerId);
        param.put("clientId", clientId);
        return  sqlSession.insert("mapper.jjimfree.insertJjimFree", param);
    }*/
}
