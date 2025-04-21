package dao.admin;

import dto.ClientUserDetail;
import dto.ClientUserInfo;

import java.util.List;

public interface IClientDAO {
    List<ClientUserInfo> selectAllClients() throws Exception;
    ClientUserDetail selectClientById(String userId) throws Exception;
    List<ClientUserInfo> selectClientsByKeyword(String keyword) throws Exception;
}
