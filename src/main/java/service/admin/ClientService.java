package service.admin;

import dao.admin.IClientDAO;
import dto.admin.ClientUserDetail;
import dto.admin.ClientUserInfo;

import java.util.List;

public class ClientService implements IClientService {

    IClientDAO clientDAO;

    public ClientService(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public List<ClientUserInfo> getAllClients() throws Exception {
        return clientDAO.selectAllClients();
    }

    @Override
    public ClientUserDetail selectClientById(String userId) throws Exception {
        return clientDAO.selectClientById(userId);
    }

    @Override
    public void addClient(ClientUserInfo clientUserInfo) throws Exception {

    }

    @Override
    public void updateClient(ClientUserInfo clientUserInfo) throws Exception {

    }

    @Override
    public void deleteClient(int clientId) throws Exception {

    }
}
