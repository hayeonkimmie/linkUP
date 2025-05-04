package dao.home;

import dto.Pay;

import java.util.List;

public interface IPayDAO {
    void insertPay(Pay pay);
    Pay selectPayByProjectIdandName(Integer projectId, String position) throws Exception;
    List<Pay> selectPayByProjectId(Integer projectId) throws Exception;

    List<Pay> getPaysByProjectId(int projectId);

    void deletePaysByProjectId(int projectId);

    Pay selectPayByProjectPayId(Integer projectPayId) throws Exception;
}
