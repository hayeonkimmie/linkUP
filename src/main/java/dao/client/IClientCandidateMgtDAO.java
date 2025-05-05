package dao.client;

import dto.ClientCandidateMgt;

import java.util.List;
import java.util.Map;

public interface IClientCandidateMgtDAO {
    List<ClientCandidateMgt> selectCandidateMgtByStatus(Map<String, Object> param) throws Exception;
    ClientCandidateMgt selectProjectInfoById(int projectId) throws Exception; //프로젝트id로 상세정보 조회
    void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception; // 지원상태 (수락 ,거절)
    void insertContract(int projectId, String freelancerId, Integer applyId, String clientId) throws Exception; // 수락하면 계약테이블에 행 추가

}