package service.home;

import dao.home.INewsDAO;
import dao.home.NewsDAOImpl;
import dto.News;
import dto.Notice;

import java.util.List;

public class NewsService implements INewsService {
    private INewsDAO newsDAO = new NewsDAOImpl();

    @Override
    public List<News> getNewsList(Integer row) throws Exception {
        return newsDAO.selectNewsList(row-1);
    }

    @Override
    public List<News> getGogakCenterNewsList(Integer row) throws Exception {
        return newsDAO.selectGogakCenterNewsList(row-1);
    }

    @Override
    public News getNewsPage(Integer id) throws Exception {
        return newsDAO.selectNewsPage(id);
    }
}
