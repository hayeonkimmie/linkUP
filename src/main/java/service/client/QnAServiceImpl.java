package service.client;

import dao.client.ClientDAOImpl;
import dao.client.IClientDAO;
import dto.QnA;
import util.PageInfo;

import java.util.List;

public class QnAServiceImpl implements IQnAService {
    private IClientDAO clientDAO;

    public QnAServiceImpl() {
        clientDAO = new ClientDAOImpl();
    }
    @Override
    public List<QnA> getQnAList(PageInfo pageInfo) throws Exception {
        Integer allCount = clientDAO.selectQnACount();
        Integer allPage = (int) Math.ceil((double)allCount/10);
        Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
        Integer endPage = startPage+10-1;
        if(endPage > allPage) endPage = allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage()-1)*10+1;


        return clientDAO.selectQnAList(row-1);
    }
}
