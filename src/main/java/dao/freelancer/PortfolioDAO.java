package dao.freelancer;

import dto.Portfolio;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioDAO implements IPortfolioDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public Integer selectPortfolioCnt(String userId) {
        return sqlSession.selectOne("mapper.portfolio.selectPortfolioCnt", userId);
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(Integer row, String userId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("row", row);
        System.out.println("PortfolioDAO.java 24" + param);
        return sqlSession.selectList("mapper.portfolio.selectPortfolioListByPage", param);
    }

    @Override
    public Portfolio selectPortfolioListById(Integer portfoId) throws Exception {
        return sqlSession.selectOne("mapper.portfolio.selectPortfolioById", portfoId);
    }

    @Override
    public void deletePortfolio(Integer num) throws Exception {
        try {
            sqlSession.update("mapper.portfolio.deletePortfolioById", num);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> projectInfoForPortfolio(String freelancerId) {
        Map<Integer, Map<String, Object>> rawResult = sqlSession.selectMap("mapper.portfolio.projectInfoForPortfolio", freelancerId, "project_id");
        Map<Integer, String> result = new HashMap<>();

        for (Map.Entry<Integer, Map<String, Object>> entry : rawResult.entrySet()) {
            Integer projectId = entry.getKey();
            Map<String, Object> projectData = entry.getValue();
            String projectName = (String) projectData.get("project_name");  // 이 부분 중요!!

            result.put(projectId, projectName);
        }
        System.out.println("projectInfoForPortfolio 54 "+result);
        return result;
//        System.out.println( sqlSession.selectMap("mapper.portfolio.projectInfoForPortfolio", userId, "project_id"));
//        return sqlSession.selectMap("mapper.portfolio.projectInfoForPortfolio", userId, "project_id");

    }

    @Override
    public Integer writePortfolio(Portfolio portfolio) throws Exception {
        Integer newPortfolioId = null;
        try {
//            if(portfolio.getIsTempSaved() == false) {
//                sqlSession.insert("mapper.portfolio.insertPortfolioComplete", portfolio);
//            } else {
//                sqlSession.insert("mapper.portfolio.insertPortfolioIncomplete", portfolio);
//            }
            sqlSession.insert("mapper.portfolio.insertPortfolioComplete", portfolio);
            sqlSession.commit();
            newPortfolioId = portfolio.getPortfolioId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPortfolioId;
    }

    @Override
    public void modifyPortfolio(Portfolio portfolio) throws Exception {
        try {
            sqlSession.update("mapper.portfolio.updatePortfolio", portfolio);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isPortfolioOwner(String userId, Integer portfolioId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", userId);
        param.put("portfolio_id", portfolioId);
        int cnt = sqlSession.selectOne("mapper.portfolio.isPortfolioOwner", param);
        return cnt != 1;
    }
}