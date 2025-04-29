package service.client;

import dto.ClientCandidateMgt;

import java.util.List;
import java.util.Map;

public interface IClientCandidateMgt {
    // 조건이 여러개일 수도 있으므로 param으로 받기 ! (키 타입은 문자열로 고정, 값은 모든 자료형[숫자, 문자열, 날짜] 등 모두 가능)
    List<ClientCandidateMgt> getCandidateMgtList(Map<String, Object> param) throws Exception;
    ClientCandidateMgt getProjectInfo(int projectId) throws Exception;
    void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception;
}
