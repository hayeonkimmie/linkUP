package service.client;

import dao.client.ClientQnADAOImpl;
import dao.client.IClientQnADAO;
import dto.QnA;
import util.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QnAServiceImpl implements IQnAService {
    private IClientQnADAO clientQnADAO;

    public QnAServiceImpl() {
        clientQnADAO = new ClientQnADAOImpl();
    }



//    사용자 ID가 있는 QnA 목록 조회

    @Override
    public List<QnA> getQnAList(String userId, PageInfo pageInfo) throws Exception {
        // 사용자 ID로 QnA 개수 조회
        Map<String, Object> countParam = new HashMap<>();
        countParam.put("userId", userId);
        Integer allCount = clientQnADAO.selectQnACountWithFilter(countParam);

        Integer allPage = (int) Math.ceil((double) allCount / 10);
        Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
        Integer endPage = Math.min(startPage + 9, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage() - 1) * 3;

        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("row", row);

        return clientQnADAO.selectQnAListWithFilter(param);
    }

//  사용자 ID와 필터가 적용된 QnA 목록 조회

    @Override
    public List<QnA> getQnAListWithFilter(String userId, PageInfo pageInfo, String status, String sort, String keyword) throws Exception {
        final int pageSize = 3;

        int curPage = Math.max(1, pageInfo.getCurPage());

        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("status", status);
        param.put("keyword", keyword);
        param.put("sort", sort);  // 정렬 파라미터 추가

        // 디버깅을 위한 로그 출력
        System.out.println("정렬 파라미터: " + sort);
        System.out.println("필터 매개변수: " + param);

        Integer allCount = clientQnADAO.selectQnACountWithFilter(param);
        Integer allPage = (int) Math.ceil((double) allCount / pageSize);
        Integer startPage = (curPage - 1) / 10 * 10 + 1;
        Integer endPage = Math.min(startPage + 9, allPage);

        pageInfo.setCurPage(curPage);
        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (curPage - 1) * pageSize;

        param.put("row", row);

        return clientQnADAO.selectQnAListWithFilter(param);
    }
}