package dao.home;

import dto.Notice;

import java.util.List;

public interface INoticeDAO {
    List<Notice> selectNoticeList(Integer row) throws Exception;
    List<Notice> selectGogakCenterNoticesList(Integer row) throws Exception;

}
