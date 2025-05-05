package service.home;


import dao.home.IPayDAO;
import dao.home.PayDAO;
import dto.Pay;

import java.util.List;

public class PayService implements IPayService {
    private final IPayDAO payDAO = new PayDAO();

    @Override
    public void registerPay(Pay pay) {
        payDAO.insertPay(pay);
    }

    @Override
    public List<Pay> selectPayByProjectId(Integer projectId) throws Exception {
        return payDAO.selectPayByProjectId(projectId);
    }
    @Override
    public List<Pay> getPaysByProjectId(int projectId) {
        // Pay 정보 가져오기 로직
        return payDAO.getPaysByProjectId(projectId);
    }

    @Override
    public void deletePaysByProjectId(int projectId) {
        // 기존 Pay 정보 삭제 로직
        payDAO.deletePaysByProjectId(projectId);
    }
}