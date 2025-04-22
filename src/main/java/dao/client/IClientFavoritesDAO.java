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
    boolean isFavoriteExists(String clientId, String freelancerId) throws Exception; //찜 여부 확인
    // 찜 토글용 전체 조회 (토글 기능 = select + insert + delete) => 버튼 하나로 상태 변경 완성
    List<ClientFavorites> selectAllFavoritesByClientId(String clientId) throws Exception;
}

