package service.client;

import dto.ClientFavorites;
import util.PageInfo;

import java.util.List;

public interface IClientFavoritesService {
    List<ClientFavorites> getClientFavorites(PageInfo pageInfo) throws Exception;

    List<ClientFavorites> getClientFavoritesWithFilter(PageInfo pageInfo, String clientId, String sort, String keyword) throws Exception;

    List<ClientFavorites> selectAllFavoritesByClientId(String clientId) throws Exception;

    String toggleFavorite(String clientId, String freelancerId) throws Exception;

    String removeFavoriteOnly(String clientId, String freelancerId) throws Exception;
    boolean isFreelancerLiked(String clientId, String freelancerId) throws Exception;
    void likeFreelancer(String freelancerId, String clientId) throws Exception;
    void cancelLikeFreelancer(String freelancerId, String clientId) throws Exception;

}