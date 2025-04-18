package dao.client;

import dto.ClientFavorites;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class ClientFavoritesDAOImpl implements IClientFavoritesDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();


    @Override
    public List<ClientFavorites> selectJjimFree(Integer row) throws Exception {
        return sqlSession.selectList("mapper.jjimfree.selectJjimFree", row);

    }
    @Override
    public Integer selectClientFavoritesCount() throws Exception {
        return sqlSession.selectOne("mapper.jjimfree.selectJjimCount");
    }
}
