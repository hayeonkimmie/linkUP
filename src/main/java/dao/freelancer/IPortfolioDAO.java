package dao.freelancer;
import dto.Portfolio;

import java.util.List;

public interface IPortfolioDAO {
    public Integer selectPortfolioCnt(String userId);
    public List<Portfolio> selectPortfolioListByPage(Integer row, String userId) throws Exception;
    public Portfolio selectPortfolioListById(Integer portfoId) throws Exception;
    public void deletePortfolio(Integer num) throws Exception;
}