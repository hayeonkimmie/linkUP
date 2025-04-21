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
        System.out.println("FreelancerDAO 21 + selectExpertFreelancerById "+freelancerId);
        Freelancer freelancer = sqlSession.selectOne("mapper.freelancer.selectExpertFreelancerById", freelancerId);
        if (freelancer == null) {
            return null;
        }
        if(freelancer.getCategory() != null) {
            String[] categoryIdsStr = freelancer.getCategory().split("\\^");
            List<String> categoryNames =  sqlSession.selectList("mapper.selectCategoryNamesBySubIds", categoryIdsStr);
            freelancer.setCategoryList(categoryNames);
        }
        if(freelancer.getSkill() != null) {
            freelancer.setPortfolioInfoMap(selectAllportfolioInfoMap(freelancerId));

            List<License> licenseList = infoSerializer.deserializeLicenseList(freelancer.getLicense());
            freelancer.setLicenseList(licenseList);
        }
        if (freelancer.getAcademic() != null) {
            List<Academic> academicList = infoSerializer.deserializeAcademicList(freelancer.getAcademic());
            freelancer.setAcademicList(academicList);
        }
        if (selectPortfolioInfoMapForProfile(freelancerId) != null) {
            Map<Integer, String> portfolioInfoMap = selectPortfolioInfoMapForProfile(freelancerId);
            freelancer.setPortfolioInfoMap(portfolioInfoMap);
        }
        System.out.println("FreelancerDAO 24 + selectExpertFreelancerById "+freelancer);
        return freelancer;
    }
    public Map<Integer, String> selectAllportfolioInfoMap(String freelancerId) throws Exception {
        return sqlSession.selectMap("mapper.portfolio.selectAllPortfolioInfoForProfile", freelancerId, "project_id");
    }
    public Map<Integer, String> selectPortfolioInfoMapForProfile(String freelancerId) throws Exception {
        return sqlSession.selectMap("mapper.portfolio.selectPortfolioInfoMapForProfile", freelancerId, "project_id");
    }

    public List<Career> selectCareerById(String freelancerId) throws Exception{
        List<Career> careerList = new ArrayList<Career>();
        try {
            careerList = sqlSession.selectList("mapper.freelancer.selectCareerById", freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return careerList;
    }
    public Map<Integer, Category> selectCategoriesAsMap() {
        Map<Integer, Category> categoriesMap = sqlSession.selectMap("mapper.subCategory.selectCategoriesAsMap", "categoryId");
        return categoriesMap;
    }

    @Override
    public void insertFreelancer(Freelancer freelancer) throws Exception {

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
