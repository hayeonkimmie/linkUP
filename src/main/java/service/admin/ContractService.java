package service.admin;

import dao.admin.IContractDAO;

public class ContractService implements IContractService{

    private static IContractDAO contractDAO;

    public ContractService(IContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    @Override
    public void updateClientStatus(int projectId, String clientStatus) throws Exception {
        contractDAO.updateClientStatus(projectId, clientStatus);
    }
}
