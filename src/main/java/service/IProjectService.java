package service;
import dto.Portfolio;
import util.PageInfo;

import java.util.List;

public interface IProjectService {
    Integer selectProjectCnt(String user_id);
    List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception;

}
