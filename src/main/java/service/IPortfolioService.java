package service;

import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public interface IPortfolioService {
    //Portfolio select_portfolio(Integer num) throws Exception;
    Integer selectPortfolioCnt(String user_id);
    List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception;
//    void modify_portfolio(Portfolio portfolio) throws Exception;
//    void delete_portfolio(Integer num) throws Exception;
//    Portfolio write_portfolio(Portfolio Portfolio) throws Exception;
}
