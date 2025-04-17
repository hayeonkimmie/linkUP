package service;

import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public interface IPortfolioService {
    //Portfolio select_portfolio(Integer num) throws Exception;
    List<Portfolio> select_portfolio_list_by_page(PageInfo pageInfo, String user_id) throws Exception;
    Integer select_portfolio_count(String user_id) throws Exception;
//    void modify_portfolio(Portfolio portfolio) throws Exception;
//    void delete_portfolio(Integer num) throws Exception;
//    Portfolio write_portfolio(Portfolio Portfolio) throws Exception;
}
