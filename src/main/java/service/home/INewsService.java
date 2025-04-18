package service.home;

import dto.News;

import java.util.List;

public interface INewsService {
    public List<News> getNewsList(Integer row) throws Exception;

    public List<News> getGogakCenterNewsList(Integer row) throws Exception;
}
