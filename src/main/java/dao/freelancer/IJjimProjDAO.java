package dao.freelancer;

import dto.JjimProj;
import util.PageInfo;

import java.util.List;

public interface IJjimProjDAO {
    public void insertJjimProj(String freelancerId, Integer projectId) throws Exception;
    public void deleteJjimProjById(Integer jjimId) throws Exception;
    public void deleteJjimProjList(List<Integer> jjimIdList) throws Exception;
    public List<JjimProj> selectJjimProjListByPage(Integer row, String userId) throws Exception;
    public Integer selectJjimProjCnt(String userId) throws Exception;
    public List<JjimProj> selectJjimProjListforMain(String userId) throws Exception;
}
