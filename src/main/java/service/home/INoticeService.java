package service.home;

import dto.Notice;
import util.PageInfo;

import java.util.List;

public interface INoticeService {
    public List<Notice> getNoticesList(Integer row) throws Exception;
    //    List<Notice> getNoticesList(PageInfo pageInfo) throws Exception;

    public List<Notice> getGogakCenterNoticesList(Integer row) throws Exception;

    public Notice getNoticePage(Integer id) throws Exception;
}
