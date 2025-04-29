package dao.client;

import dto.ClientCandidateMgt;

import java.util.List;
import java.util.Map;

public interface IClientCandidateMgtDAO {
    List<ClientCandidateMgt> selectCandidateMgtByStatus(Map<String, Object> param) throws Exception;
    ClientCandidateMgt selectProjectInfoById(int projectId) throws Exception; //프로젝트id로 상세정보 조회
    void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception;
}