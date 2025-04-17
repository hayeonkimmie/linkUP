package dao.freelancer;
import dto.Portfolio;

import java.util.List;

public interface IPortfolioDAO {
    public Integer selectPortfolioCnt(String user_id);
    public List<Portfolio> selectPortfolioListByPage(Integer row, String user_id) throws Exception;
    //public Portfolio select_portfolio(Integer num) throws Exception;
}