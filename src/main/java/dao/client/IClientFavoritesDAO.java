package dao.client;

import dto.ClientFavorites;
import util.PageInfo;

import java.util.List;
import java.util.Map;

public interface IClientFavoritesDAO {
    List<ClientFavorites> selectJjimFree(Integer row) throws Exception; // 페이징
    Integer selectClientFavoritesCount() throws Exception; // 찜한 개수
    List<Map<String, Object>> selectFavoritesCard(Map<String, Object> param) throws Exception; // 찜 상세글
    int selectFavoritesWithFilter(Map<String, Object> param); // 정렬 처리
    void insertFavorite(Map<String, String> param); //찜 등록
    void deleteFavorite(Map<String, String> param); // 찜 해제

    // 찜 토글 처리
    void toggleFavorite(Map<String, String> param) throws Exception;
}

