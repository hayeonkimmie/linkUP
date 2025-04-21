package dao.client;

import dto.Client;
import dto.QnA;

import java.util.List;

public interface IClientQnADAOImpl {
    List<QnA> selectQnAList(Integer row) throws Exception;
    Integer selectQnACount() throws Exception;

}
