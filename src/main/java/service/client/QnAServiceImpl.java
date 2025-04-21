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

    // 기존 전체 QnA 리스트
    @Override
    public List<QnA> getQnAList(PageInfo pageInfo) throws Exception {
        Integer allCount = clientQnADAO.selectQnACount();
        Integer allPage = (int) Math.ceil((double) allCount / 10);
        Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
        Integer endPage = Math.min(startPage + 9, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage() - 1) * 3;
        return clientQnADAO.selectQnAList(row);
    }

    // 필터 및 정렬 기능 추가된 QnA 리스트
    @Override
    public List<QnA> getQnAListWithFilter(PageInfo pageInfo, String status, String sort, String keyword) throws Exception {
        final int pageSize = 3;

        int curPage = Math.max(1, pageInfo.getCurPage());

        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("keyword", keyword);
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
        param.put("status", status);
        param.put("sort", sort);
        param.put("keyword", keyword);

        return clientQnADAO.selectQnAListWithFilter(param);
    }
}
