package service.freelancer;

import dao.freelancer.ApplyProjDAO;
import dao.freelancer.IApplyProjDAO;
import dto.Apply;
import org.apache.ibatis.annotations.Select;
import util.PageInfo;

import java.util.List;

public class FreelancerApplyProjService implements IFreelancerApplyProjService {
    IApplyProjDAO iApplyProjDAO;
    public FreelancerApplyProjService() {
        super();
        iApplyProjDAO = new ApplyProjDAO();
    }
    public List<Apply> getApplProjyList(String freelancerId, PageInfo pageInfo) throws Exception {
        Integer ApplyProjCount = null;
        try {
            ApplyProjCount = iApplyProjDAO.getApplyProjCnt(freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("JjimProjService.java 18 ApplyProjCount = " + ApplyProjCount);
        Integer allPage = (int)Math.ceil((double)ApplyProjCount/10);

        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        Integer row = (pageInfo.getCurPage()-1)*10+1;
        System.out.println("row= "+ row + "endPage =" +endPage);
        List<Apply> applProjyList = iApplyProjDAO.getApplyProjList(row-1, freelancerId);
        System.out.println("FreelancerApplyProjService.java 35 = " + applProjyList);
        return applProjyList;
    }
    public Integer getApplyProjCnt(String freelancerId) throws Exception {
        return iApplyProjDAO.getApplyProjCnt(freelancerId);
    }
    public void cancleProjApply (Integer applyId) throws Exception {
        System.out.println("FreelancerApplyProjService.java 44 = " + applyId);
        try {
            iApplyProjDAO.cancleProjApply(applyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
