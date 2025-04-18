package service.home;

import dao.home.INoticeDAO;
import dao.home.NoticeDAOImpl;
import dto.Notice;

import java.util.List;

public class NoticeService implements INoticeService {
    private INoticeDAO noticeDAO = new NoticeDAOImpl();
//    public List<Notice> getNoticesList(PageInfo pageInfo) throws Exception {
    @Override
    public List<Notice> getNoticesList(Integer row) throws Exception {
//        Integer row = (pageInfo.getCurPage()-1)*10+1;
        return noticeDAO.selectNoticeList(row -1 );
    }

    @Override
    public List<Notice> getGogakCenterNoticesList(Integer row) throws Exception {
        return noticeDAO.selectNoticeList(row -1);
    }
}
