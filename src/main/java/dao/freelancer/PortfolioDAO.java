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
    public Integer selectPortfolioCnt(String user_id) {
        System.out.println("user_id:"+user_id);
        return sqlSession.selectOne("mapper.portfolio.selectPortfolioCnt", user_id);
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(Integer row, String user_id) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", user_id);
        param.put("row", row);
        System.out.println(param);
        return sqlSession.selectList("mapper.portfolio.selectPortfolioListByPage",param);
    }
/*
    @Override
    public Portfolio select_portfolio(Integer portfolio_id) throws Exception {
        return sqlSession.selectOne("mapper.portfolio.select_portfolio_by_Id", portfolio_id);
    }*/

/*    @Override
    public void deletePortfolio(Integer num) throws Exception {
        sqlSession.delete("mapper.portfolio.deleteArticle", num);
        sqlSession.commit();
    }
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
