package dao.home;

import dto.Notice;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;

public class NoticeDAOImpl implements INoticeDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<Notice> selectNoticeList(Integer row) throws Exception {
        List<Notice> noticeList = sqlSession.selectList("mapper.notice.selectNoticeList", row);

        for(Notice n : noticeList){
            System.out.println(n.getTitle());
        }
        return noticeList;
    }

    @Override
    public List<Notice> getGogakCenterNoticesList(Integer row) throws Exception {
        List<Notice> noticeList = sqlSession.selectList("mapper.notice.selectGogakCenterNoticeList", row);

        for (Notice n : noticeList){
            System.out.println(n.getTitle());
        }

        return noticeList;
    }
}
