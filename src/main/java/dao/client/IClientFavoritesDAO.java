package dao.client;

import dto.ClientFavorites;
import util.PageInfo;

import java.util.List;

public interface IClientFavoritesDAO {
    List<ClientFavorites> selectJjimFree(Integer row) throws Exception;
    Integer selectClientFavoritesCount() throws Exception;
}
