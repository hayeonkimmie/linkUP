package service.freelancer;

import dto.JjimProj;
import util.PageInfo;

import java.util.List;

public interface IJjimProjService {
    List<JjimProj> selectJjimProjListByPage(PageInfo pageInfo, String userId) throws Exception;
    Integer selectJjimProjCnt(String userId) throws Exception;
    /*void insertJjimProj(JjimProj jjimProj) throws Exception;*/
    void deleteJjimProjById(Integer jjimId) throws Exception;
    void deleteJjimProjList(List<Integer> JjimIdLsit) throws Exception;

}
