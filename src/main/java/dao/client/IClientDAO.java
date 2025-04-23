package dao.client;

import dto.Client;
import dto.QnA;

import java.util.List;

public interface IClientDAO {
    List<QnA> selectQnAList(Integer row) throws Exception;

    Integer selectQnACount() throws Exception;

    int insertClient(Client client);
    boolean existsClientById(String id);
}
