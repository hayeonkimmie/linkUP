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
        sqlSession.insert("mapper.jjimfree.insertFavorite", param);
        // 데이터 변경하는 쿼리라 반드새 commit() 필수
        sqlSession.commit();
    }

    // 찜 해제
    @Override
    public void deleteFavorite(Map<String, String> param) {
        sqlSession.delete("mapper.jjimfree.deleteFavorite", param);
        sqlSession.commit();
    }

    @Override
    public void toggleFavorite(Map<String, String> param) throws Exception {
        return "";
    }
}