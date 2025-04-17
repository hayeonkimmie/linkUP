package dao.admin;

import dto.admin.ClientUserDetail;
import dto.admin.ClientUserInfo;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class ClientDAO implements IClientDAO {

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<ClientUserInfo> selectAllClients() throws Exception {

        return sqlSession.selectList("mapper.aclient.selectAllClients");
    }

    @Override
    public ClientUserDetail selectClientById(String userId) throws Exception {
        return sqlSession.selectOne("mapper.aclient.selectClientById", userId);
    }
}
