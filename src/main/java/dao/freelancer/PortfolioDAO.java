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
        System.out.println("PortfolioDAO.java 24"+ param);
        return sqlSession.selectList("mapper.portfolio.selectPortfolioListByPage",param);
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
    /*
    @Override
    public Portfolio insertPortfolio(Portfolio Portfolio) throws Exception {
        try {
            sqlSession.insert("mapper.portfolio.selectArticleListByPage", portfolio);
//			sqlSession.select("");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return portfolio;
    }

	@Override
	public void updateArticle(Article article) throws Exception {
		sqlSession.update("mapper.article.updateArticle", article);
//		sqlSession.update("mapper.article.updateArticle", param);
		sqlSession.commit();
	}
}
    */
}
