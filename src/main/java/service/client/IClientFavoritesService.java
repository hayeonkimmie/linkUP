package service.client;

import dto.ClientFavorites;
import util.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IClientFavoritesService {
    List<ClientFavorites> getClientFavorites(PageInfo pageInfo) throws Exception;
    List<ClientFavorites> getClientFavoritesWithFilter(PageInfo pageInfo, String status, String sort, String keyword) throws Exception;
    String toggleFavorite(String clientId, String freelancerId) throws Exception;

}
