package dao.freelancer;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import dto.Portfolio;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

public class PortfolioDAO implements IPortfolioDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public Integer select_portfolio_count(String user_id) {
        return sqlSession.selectOne("mapper.portfolio.select_portfolio_cnt", user_id);
    }

    @Override
    public List<Portfolio> select_portfolio_list_by_page(Integer row, String user_id) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", user_id);
        param.put("row", row);
        return sqlSession.selectList("mapper.portfolio.select_portfolio_list_by_page",param);
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
