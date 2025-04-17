package service;

import java.util.List;
import dao.freelancer.IPortfolioDAO;
import dto.Portfolio;
import dao.freelancer.PortfolioDAO;
import util.PageInfo;

public class PortfolioService implements IPortfolioService{
    private IPortfolioDAO iportfolioDAO;
    public PortfolioService() {
        super();
        iportfolioDAO = new PortfolioDAO();
    }

    @Override
    public Integer select_portfolio_count(String user_id) {
        return iportfolioDAO.select_portfolio_count(user_id);
    }
    @Override
    public List<Portfolio> select_portfolio_list_by_page(PageInfo page_info, String user_id) throws Exception {
        Integer portfolio_cnt = iportfolioDAO.select_portfolio_count(user_id);
        if(portfolio_cnt == null) {
            portfolio_cnt = 0;
        }
        Integer all_page = (int)Math.ceil((double)portfolio_cnt/10);
        Integer start_page = (page_info.get_cur_page()-1)/10*10+1;
        Integer end_page = start_page+10-1; //10,20,30,40...
        if(end_page>all_page) end_page=all_page;

        page_info.set_all_page(all_page);
        page_info.set_start_page(start_page);
        page_info.set_end_page(end_page);

        Integer row = (page_info.get_cur_page()-1)*10+1;
        List<Portfolio> portfolio_list = iportfolioDAO.select_portfolio_list_by_page(row, user_id);
        return portfolio_list;
    }
/*    @Override
    public Portfolio select_portfolio(Integer num) throws Exception {

        return null;
    }*/
/*    @Override
    Portfolio write_portfolio(Portfolio Portfolio) throws Exception{
    }
    @Override
    void modify_portfolio(Portfolio portfolio) throws Exception {

    }
    @Override
    void delete_portfolio(Integer num) throws Exception {

    }*/
}
