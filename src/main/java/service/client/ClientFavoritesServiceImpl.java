package service.client;

import dao.client.ClientFavoritesDAOImpl;
import dao.client.IClientFavoritesDAO;
import dto.ClientFavorites;
import util.PageInfo;

import java.util.List;

public class ClientFavoritesServiceImpl implements IClientFavoritesService {
    private IClientFavoritesDAO clientFavoritesDAO ;


    public ClientFavoritesServiceImpl(){
        clientFavoritesDAO = new ClientFavoritesDAOImpl();}

    @Override
    public List<ClientFavorites> getClientFavorites(PageInfo pageInfo) throws Exception {
        Integer allCount = clientFavoritesDAO.selectClientFavoritesCount();
        Integer allPage = (int) Math.ceil((double) allCount / 10);
        Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
        Integer endPage = startPage + 10 - 1;
        if (endPage > allPage) endPage = allPage;

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        Integer row = (pageInfo.getCurPage() - 1) * 10 + 1;

        return clientFavoritesDAO.selectJjimFree(row-1);
    }
    }

