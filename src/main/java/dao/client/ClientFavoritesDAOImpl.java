package dao.client;

import dto.ClientFavorites;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.*;

public class ClientFavoritesDAOImpl implements IClientFavoritesDAO {
    private final SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<ClientFavorites> selectJjimFree(Integer row) throws Exception {
        return sqlSession.selectList("mapper.jjimfree.selectJjimFree", row);
    }

    @Override
    public Integer selectClientFavoritesCount() throws Exception {
        return sqlSession.selectOne("mapper.jjimfree.selectJjimCount");
    }

    // 카드 데이터 (프로필, 평점, 프로젝트 수, 태그 등) 조회
    @Override
    public List<Map<String, Object>> selectFavoritesCard(Map<String, Object> param) throws Exception {
        return sqlSession.selectList("mapper.jjimfree.selectFavoritesByClient", param);
    }

    // 필터된 총 개수 조회 (정렬/검색 조건 포함)
    @Override
    public int selectFavoritesWithFilter(Map<String, Object> param) {
        return sqlSession.selectOne("mapper.jjimfree.selectFavoritesCountWithFilter", param);
    }

    /* 찜 등록 및 해제*/
    // 찜 등록
    @Override
    public void insertFavorite(Map<String, String> param) {
        System.out.println("ClientFavoritesDAOImpl.java 38" + param);
        sqlSession.insert("mapper.jjimfree.insertFavorite", param);
        // 데이터 변경하는 쿼리라 반드새 commit() 필수
        sqlSession.commit();
    }

    // 찜 해제
    @Override
    // DB에서 지우는거라 반환값 X
    public void deleteFavorite(Map<String, String> param) {
        sqlSession.delete("mapper.jjimfree.deleteFavorite", param);
        sqlSession.commit();
    }

    // 찜 등록 여부 확인

    @Override
    public boolean isFavoriteExists(String clientId, String freelancerId) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("clientId", clientId);
        param.put("freelancerId", freelancerId);
        Integer count = sqlSession.selectOne("mapper.jjimfree.existsFavorite", param);
        return count != null && count > 0;
    }



    @Override
    public List<ClientFavorites> selectAllFavoritesByClientId(String clientId) throws Exception {
        // 찜 여부 확인을 위해 데이터 자체 받아오기
        return sqlSession.selectList("mapper.jjimfree.selectAllFavoritesByClientId", clientId);
    }
}

