package service;
//
//import java.util.ArrayList;
import java.util.*;

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
    public Integer selectPortfolioCnt(String user_id) {
        return iportfolioDAO.selectPortfolioCnt(user_id);
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(PageInfo page_info, String user_id) throws Exception {
        Integer portfolioCnt = iportfolioDAO.selectPortfolioCnt(user_id);
        if(portfolioCnt == null) {
            portfolioCnt = 0;
        }
        if(portfolioCnt == 0) {
            return null;
        }
        Integer allPage = (int)Math.ceil((double)portfolioCnt/10);
        Integer startPage = (page_info.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        page_info.setAllPage(allPage);
        page_info.setStartPage(startPage);
        page_info.setEndPage(endPage);

        Integer row = (page_info.getCurPage()-1)*10+1;
        try {

        }finally {

        }
        List<Portfolio> portfolioList = iportfolioDAO.selectPortfolioListByPage(row, user_id);
        if(portfolioList == null || portfolioList.isEmpty()) {
            return null;
        } else if(portfolioList.contains("\\^&\\^")){
            return portfolioList;
        }

        List<Portfolio> portfolioList2 = new ArrayList<>();

        //portfolio_id, title, thumbnail, introduce, skill_description, created_date, is_temp_saved
        for (Portfolio p : portfolioList) {
            Portfolio portfolio = new Portfolio();
            portfolio.setPortfolio_id(p.getPortfolioId());
            portfolio.setTitle(p.getTitle());
            portfolio.setThumbnail(p.getThumbnail());
            portfolio.setIntroduce(p.getIntroduce());
            String[] skills = p.getSkillDescription().split("\\^&\\^"); // 정규식 특수문자 escape 필요
            portfolio.setSkillList(skills);
            portfolio.setCreated_date(p.getCreatedDate());
            portfolio.setTeam_role(p.isTempSaved());
            portfolioList2.add(portfolio);
        }

        for (Portfolio p : portfolioList2){
            System.out.println(p.getPortfolioId());
            System.out.println(p.getTitle());
            System.out.println(p.getSkillList());
        }
        return portfolioList2;
    }
}
