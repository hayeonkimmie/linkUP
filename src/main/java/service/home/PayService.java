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
    public List<Pay> getPayByProjectId(int projectId) throws Exception {
        return List.of();
    }
}