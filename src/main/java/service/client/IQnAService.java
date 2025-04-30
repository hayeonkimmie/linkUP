package service.client;

import dto.QnA;
import util.PageInfo;

import java.util.List;

public interface IQnAService {
    // 기존 전체 QnA 리스트
    List<QnA> getQnAList(String userId, PageInfo pageInfo) throws Exception;
    // 필터 및 정렬 기능 추가된 QnA 리스트
    List<QnA> getQnAListWithFilter(String userId, PageInfo pageInfo, String status, String sort, String keyword) throws Exception;
}