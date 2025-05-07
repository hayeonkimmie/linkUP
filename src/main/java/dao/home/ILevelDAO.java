package dao.home;

import dto.Level;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface ILevelDAO {
    List<Level> selectAllLevel();
    List<Level> levelList(List<Integer> levelIdList);
}