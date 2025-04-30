package service.home;


import dao.home.ILevelDAO;
import dao.home.LevelDAOImpl;
import dto.Level;
import java.util.List;

public class LevelServiceImpl implements ILevelService {
    private final ILevelDAO levelDAO = new LevelDAOImpl();

    @Override
    public List<Level> getAllLevel() {
        return levelDAO.selectAllLevel();
    }
}