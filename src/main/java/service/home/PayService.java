package service.home;


import dao.home.IPayDAO;
import dao.home.PayDAO;
import dto.Pay;

public class PayService implements IPayService {
    private final IPayDAO payDAO = new PayDAO();

    @Override
    public void registerPay(Pay pay) {
        payDAO.insertPay(pay);
    }
}