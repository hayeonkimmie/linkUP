package dao.client;

import dto.QnA;

import java.util.List;

public interface IClientInquiryDAO {
    public Integer selectInquiryCnt(String user_id);
    public List<QnA> selectPortfolioListByPage(Integer row, String user_id) throws Exception;

}

