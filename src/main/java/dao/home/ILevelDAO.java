package dao.home;

import dto.Level;
import java.util.List;

public interface ILevelDAO {
    List<Level> selectAllLevel();
}