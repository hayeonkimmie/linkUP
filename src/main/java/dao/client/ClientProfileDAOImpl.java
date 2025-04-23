package dao.client;

import dto.ClientProfile;
import dto.ClientUserDetail;
import dto.ClientUserInfo;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.Map;

public class ClientProfileDAOImpl implements IClientProfileDAO {
    private final SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();


    @Override
    public Map<String, Object> selectClientProfile(String userId) throws Exception {
        return sqlSession.selectOne("mapper.client.selectClientProfile", userId);
    }

    @Override
    public int updateClientProfile(ClientProfile profile) throws Exception {
        int result = sqlSession.update("mapper.client.updateClientProfile", profile); // 프로필 전달받음
        sqlSession.commit(); // 업데이트 한 내용 커밋
        return result;
    }

    @Override
    public String getPasswordByUserId(String userId) throws Exception {
        return sqlSession.selectOne("mapper.client.getPasswordByUserId", userId);

    }

    @Override
    public void updatePassword(String userId, String newPassword) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("userId", userId);
        param.put("newPassword", newPassword);
        sqlSession.update("mapper.client.updatePassword", param);
        sqlSession.commit(); // 비밀번호 업데이트 반영

    }
}
