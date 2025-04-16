package dao.freelancer;
import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public interface IPortfolioDAO {
    public Integer select_portfolio_count(String user_id);
    public List<Portfolio> select_portfolio_list_by_page(Integer row, String user_id) throws Exception;
    //public Portfolio select_portfolio(Integer num) throws Exception;
}