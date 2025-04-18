package service.client;

import dto.QnA;
import util.PageInfo;

import java.util.List;

public interface IQnAService {
    List<QnA> getQnAList(PageInfo pageInfo) throws Exception;
    List<QnA> getQnAListWithFilter(PageInfo pageInfo, String status, String sort, String keyword) throws Exception;

}
