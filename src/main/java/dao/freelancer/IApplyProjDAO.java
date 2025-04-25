package dao.freelancer;

import dto.Apply;
import util.PageInfo;

import java.util.List;

public interface IApplyProjDAO {
    Integer getApplyProjCnt(String freelancerId) throws Exception;
    void cancleProjApply (Integer applyId) throws Exception;
    List<Apply> getApplyProjList(Integer row, String freelancerId) throws Exception;
}
