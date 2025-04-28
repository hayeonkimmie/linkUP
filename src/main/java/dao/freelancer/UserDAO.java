package dao.freelancer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.MybatisSqlSessionFactory;

import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    @Override
    public int countNickname(String nickname) {
        try {
            return sqlSession.selectOne("mapper.user.checkNickName", nickname);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getPasswordById(String userId) {
        try {
            return sqlSession.selectOne("mapper.user.checkPassWord", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
