package service.freelancer;

import dto.Apply;
import util.PageInfo;

import java.util.List;

public interface IFreelancerApplyProjService {
    public List<Apply> getApplProjyList(String freelancerId, PageInfo pageInfo) throws Exception;
    public Integer getApplyProjCnt(String freelancerId) throws Exception;
    public void cancleProjApply (Integer applyId) throws Exception;
}
