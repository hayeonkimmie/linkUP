package service.client;

import dao.client.ClientCandidateMgtDAOImpl;
import dao.client.IClientCandidateMgtDAO;
import dto.ClientCandidateMgt;

import java.util.List;
import java.util.Map;

public class ClientCandidateMgtImpl implements IClientCandidateMgt{
    private final IClientCandidateMgtDAO clientCandidateMgtDAO;
    public ClientCandidateMgtImpl() {
        clientCandidateMgtDAO = new ClientCandidateMgtDAOImpl();
    }

    @Override
    public List<ClientCandidateMgt> getCandidateMgtList(Map<String, Object> param) throws Exception {
        return clientCandidateMgtDAO.selectCandidateMgtByStatus(param);
    }

    @Override
    public ClientCandidateMgt getProjectInfo(int projectId) throws Exception {
        return clientCandidateMgtDAO.selectProjectInfoById(projectId);
    }

    @Override
    public void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception {
        clientCandidateMgtDAO.updateApplyStatus(projectId, freelancerId, applyStatus);
    }

    /// 수락 시 계약테이블에 행 추가
    @Override
    public void insertContract(int projectId, String freelancerId, Integer applyId, String clientId) throws Exception {
        clientCandidateMgtDAO.insertContract(projectId, freelancerId, applyId, clientId);

    }


}
