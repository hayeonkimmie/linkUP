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
    public Integer selectPortfolioCnt(String userId) {
        return iportfolioDAO.selectPortfolioCnt(userId);
    }

    @Override
    public List<Portfolio> selectPortfolioListByPage(PageInfo pageInfo, String userId) throws Exception {
        Integer portfolioCnt = iportfolioDAO.selectPortfolioCnt(userId);
        System.out.println("PortfolioService.java 26 portfolioCnt "+portfolioCnt);
        if(portfolioCnt == null || portfolioCnt == 0) {
            return null;
        }
        Integer allPage = (int)Math.ceil((double)portfolioCnt/10);
        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage()-1)*10+1;

        List<Portfolio> portfolioList = iportfolioDAO.selectPortfolioListByPage(row-1, userId);
        if(portfolioList == null || portfolioList.isEmpty()) {
            System.out.println(" PortfolioService.java 포트폴리오가 없습니다.");
            return null;
        }

        List<Portfolio> portfolioList2 = new ArrayList<>();

        for (Portfolio p : portfolioList) {
            System.out.println(p.toString());
            Portfolio portfolio = new Portfolio();
            portfolio.setPortfolioId(p.getPortfolioId());
            portfolio.setTitle(p.getTitle());
            portfolio.setThumbnail(p.getThumbnail());
            portfolio.setIntroduce(p.getIntroduce());

            // 스킬 분리 처리 (null check 필수)
            if (p.getSkillDescription() != null && p.getSkillDescription().contains("^")) {
                String[] skills = p.getSkillDescription().split("\\^");
                portfolio.setSkillList(skills);
            } else {
                portfolio.setSkillList(new String[]{ p.getSkillDescription() }); // 단일 스킬일 경우
            }

            portfolio.setCreatedDate(p.getCreatedDate());
            portfolio.setTempSaved(p.getIsTempSaved());
            portfolioList2.add(portfolio);
        }

        return portfolioList2;
    }

    @Override
    public Portfolio selectPortfolioListById(Integer portfoId) throws Exception {
//        Portfolio tempportfolio = iportfolioDAO.selectPortfolioListById(portfoId);
//        Portfolio portfolio = new Portfolio();
        Portfolio portfolio = iportfolioDAO.selectPortfolioListById(portfoId);
        if(portfolio == null) {
            System.out.println("PortfolioService.java 83 포트폴리오가 없습니다.");
            return null;
        } else {
            portfolio.setSkillList(portfolio.getSkillDescription().split("\\^"));
            portfolio.setAttachmentList(portfolio.getAttachment().split("\\^"));
            portfolio.setExternalUrlList(portfolio.getExternalUrl().split("\\^"));
            System.out.println(portfolio.toString());
        }
        return portfolio;
    }
    @Override
    public void deletePortfolio(Integer num) throws Exception {
        iportfolioDAO.deletePortfolio(num);
    }
}
