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
            sqlSession.delete("mapper.portfolio.deletePortfolioById", num);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> projectInfoForProtfolio(String userId) {
        return sqlSession.selectOne("mapper.project.projectInfoForProtfolio", userId);
    }

    @Override
    public Integer writePortfolio(Portfolio portfolio) throws Exception {
        try {
            if(portfolio.getIsTempSaved() == true) {
                sqlSession.insert("mapper.portfolio.insertPortfolioIncomplete", portfolio);
            } else {
                sqlSession.insert("mapper.portfolio.insertPortfolioComplete", portfolio);
            }
//			sqlSession.select("");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return portfolio.getPortfolioId();
    }

    @Override
    public void modifyPortfolio(Portfolio portfolio) throws Exception {
        try {
            sqlSession.update("mapper.article.updatePortfolio", portfolio);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}