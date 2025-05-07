package dao.home;

import dto.Level;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.MybatisSqlSessionFactory; // 네가 사용하는 factory
import util.SingleTonSession;

import java.util.ArrayList;
import java.util.List;

public class LevelDAOImpl implements ILevelDAO {

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();
    @Override
    public List<Level> selectAllLevel() {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectList("mapper.level.selectAllLevel");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }

    @Override
    public List<Level> levelList(List<Integer> levelIdList) {
        System.out.println("가져와야 할 levelIdList : " + levelIdList);
        List<Level> levelList = new ArrayList<>();
        try(SqlSession session = this.sqlSession.openSession()) {
            for (Integer levelId : levelIdList) {
                levelList.add(session.selectOne("mapper.level.selectLvById", levelId));
            }
            return levelList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }
}