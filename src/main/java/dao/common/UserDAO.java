package dao.common;

import dto.User;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

public class UserDAO implements IUserDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    @Override
    public int insertUser(User user) {
        int result = 0 ;
        try {
            result = sqlSession.insert("mapper.user.insertUser", user);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }
    @Override
    public User selectUserById(String id) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            user = sqlSession.selectOne("mapper.user.selectUserById", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return user;
    }

}
