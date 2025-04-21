package service.freelancer;

import dao.freelancer.IJjimProjDAO;
import dao.freelancer.JjimProjDAO;
import dto.JjimProj;
import util.PageInfo;

import java.util.List;

public class JjimProjService implements IJjimProjService{
    private IJjimProjDAO iJjimProjDAO;
    public JjimProjService() {
        super();
        iJjimProjDAO = new JjimProjDAO();
    }
    @Override
    public List<JjimProj> selectJjimProjListByPage(PageInfo pageInfo, String freelancerId) throws Exception {
        Integer JjimProjCnt = selectJjimProjCnt(freelancerId);
        if(JjimProjCnt == null) {
            return null;
        }
        System.out.println("JjimProjService.java 22 JjimProjCnt = " + JjimProjCnt);
        Integer allPage = (int)Math.ceil((double)JjimProjCnt/10);

        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1; //10,20,30,40...
        if(endPage>allPage) endPage=allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        System.out.println("JjimProjService.java 38 pageInfo.getCurPage() = " + pageInfo.getCurPage());

        Integer row = (pageInfo.getCurPage()-1)*10+1;
        List<JjimProj> jjimProjList = null;
        try{
            jjimProjList = iJjimProjDAO.selectJjimProjListByPage(row-1, freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jjimProjList;
    }

    @Override
    public Integer selectJjimProjCnt(String freelancerId) throws Exception {
        return iJjimProjDAO.selectJjimProjCnt(freelancerId);
    }

    @Override
    public void deleteJjimProjById(Integer jjimId) throws Exception {
        iJjimProjDAO.deleteJjimProjById(jjimId);
    }

    @Override
    public void deleteJjimProjList(List<Integer> jjimIdList) throws Exception {
        iJjimProjDAO.deleteJjimProjList(jjimIdList);
    }
/*    @Override
    public void insertJjimProj(JjimProj jjimProj) throws Exception {

    }*/
}
