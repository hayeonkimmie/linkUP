package dao.client;

import dto.Client;
import dto.QnA;

import java.util.List;

public interface IClientQnADAOImpl2 {
    List<QnA> selectQnAList(Integer row) throws Exception;
    Integer selectQnACount() throws Exception;
    int insertClient(Client client);
}
