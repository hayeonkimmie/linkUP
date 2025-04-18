package service.client;

import dto.QnA;
import util.PageInfo;

import java.util.List;

public interface IQnAService {
    List<QnA> getQnAList(PageInfo pageInfo) throws Exception;

}
