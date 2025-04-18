package dao.home;

import dto.News;

import java.util.List;

public interface INewsDAO {
    List<News> selectNewsList(Integer row) throws Exception;

    List<News> selectGogakCenterNewsList(Integer row)throws Exception;
}
