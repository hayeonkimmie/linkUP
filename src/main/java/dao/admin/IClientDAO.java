package dao.admin;

import dto.admin.ClientUserDetail;
import dto.admin.ClientUserInfo;

import java.util.List;

public interface IClientDAO {
    List<ClientUserInfo> selectAllClients() throws Exception;
    ClientUserDetail selectClientById(String userId) throws Exception;
}
