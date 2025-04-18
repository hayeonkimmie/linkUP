package service;

import dto.Portfolio;
import util.PageInfo;

import java.util.List;
import java.util.Map;

public interface IPortfolioService {
    Portfolio selectPortfolioListById(Integer num) throws Exception;
    Integer selectPortfolioCnt(String userId) throws Exception;
    List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception;
    void modifyPortfolio(Portfolio portfolio) throws Exception;
    void deletePortfolio(Integer num) throws Exception;
    Integer writePortfolio(Portfolio Portfolio) throws Exception;
    Map<Integer, String> projectInfoForProtfolio(String userId) throws Exception;
}
