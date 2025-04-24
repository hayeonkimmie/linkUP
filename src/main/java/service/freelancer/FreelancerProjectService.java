package service.freelancer;

import dao.freelancer.IProjectDAO;
import dao.freelancer.ProjectDAO;
import dto.FreelancerProject;
import util.PageInfo;

import java.util.List;

public class FreelancerProjectService implements IFreelancerProjectService {
     private IProjectDAO projectDAO;
     private PageInfo pageInfo;
     public FreelancerProjectService() {
         super();
         projectDAO = new ProjectDAO();
     }

     @Override
     public List<FreelancerProject> selectOngoingProject(PageInfo pageInfo, String freelancerId) throws Exception {
         Integer projCnt = cntOngoingProjects(freelancerId);
         if(projCnt == null) {
             return null;
         }
         System.out.println("FreelancerProjectService.java 24 진행중인 프로젝트 갯수 = " + projCnt);
         Integer allPage = (int)Math.ceil((double)projCnt/10);

         Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
         Integer endPage = startPage+10-1; //10,20,30,40...
         if(endPage>allPage) endPage=allPage;

         pageInfo.setAllPage(allPage);
         pageInfo.setStartPage(startPage);
         pageInfo.setEndPage(endPage);
         System.out.println("JjimProjService.java 38 pageInfo.getCurPage() = " + pageInfo.getCurPage());

         Integer row = (pageInfo.getCurPage()-1)*10+1;
         return projectDAO.selectOngoingProject(row, freelancerId);
    }
    public List<FreelancerProject> selectOngoingProjectForMain(String freelancerId) throws Exception {
         Integer projCnt = cntOngoingProjects(freelancerId);
         if(projCnt == null) {
             return null;
         }
         System.out.println("FreelancerProjectService.java 24 진행중인 프로젝트 갯수 = " + projCnt);
         return projectDAO.selectOngoingProjectForMain(freelancerId);
    }

     @Override
     public List<FreelancerProject> selectCompletedProject(PageInfo pageInfo, String freelancerId) throws Exception {
         Integer projCnt = cntCompletedProjects(freelancerId);
         if(projCnt == null) {
             return null;
         }
         System.out.println("FreelancerProjectService.java 46 완료된 프로젝트 갯수 = " + projCnt);
         Integer allPage = (int)Math.ceil((double)projCnt/10);

         Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
         Integer endPage = startPage+10-1; //10,20,30,40...
         if(endPage>allPage) endPage=allPage;

         pageInfo.setAllPage(allPage);
         pageInfo.setStartPage(startPage);
         pageInfo.setEndPage(endPage);
         System.out.println("JjimProjService.java 38 pageInfo.getCurPage() = " + pageInfo.getCurPage());

         Integer row = (pageInfo.getCurPage()-1)*10+1;
         return projectDAO.selectCompletedProject(row, freelancerId);
    }
    @Override
    public Integer cntOngoingProjects(String freelancerId) throws Exception {
        return projectDAO.cntOngoingProjects(freelancerId);
    }

    @Override
    public Integer cntCompletedProjects(String freelancerId) throws Exception {
        return projectDAO.cntCompletedProjects(freelancerId);
    }
}
