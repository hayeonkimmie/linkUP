package service.client;

import dto.ClientFavorites;
import util.PageInfo;

import java.util.List;

public interface IClientFavoritesService {
    List<ClientFavorites> getClientFavorites(PageInfo pageInfo) throws Exception;
}
