package dao.home;

import dto.News;
import dto.Notice;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class NewsDAOImpl implements INewsDAO{
    public SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    @Override
    public List<News> selectNewsList(Integer row) throws Exception {
        List<News> newsList = sqlSession.selectList("mapper.news.selectNewsList", row);
        for(News n : newsList){
            System.out.println(n.getTitle());
        }
        return newsList;
    }

    @Override
    public List<News> selectGogakCenterNewsList(Integer row) throws Exception {
        List<News> newsList = sqlSession.selectList("mapper.news.selectGogakCenterNewsList", row);
        for(News n : newsList){
            System.out.println(n.getTitle());
        }
        return newsList;
    }

    @Override
    public News selectNewsPage(Integer id) throws Exception {
        return sqlSession.selectOne("mapper.news.selectNewsById", id);
    }
}
