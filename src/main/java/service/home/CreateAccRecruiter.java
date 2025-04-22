package service.home;

import dao.client.ClientDAOImpl;
import dao.client.IClientDAO;
import dao.common.IUserDAO;
import dao.common.UserDAO;
import dto.Client;
import dto.User;

public class CreateAccRecruiter implements ICreateAccRecruiter {

    private IUserDAO userDAO = new UserDAO();
    private IClientDAO clientDAO = new ClientDAOImpl();

    @Override
    public boolean registerRecruiter(User user, Client client) {
        boolean isSuccess = false;
        try {
            int userResult = userDAO.insertUser(user);
            int clientResult = clientDAO.insertClient(client);
            if (userResult > 0 && clientResult > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
