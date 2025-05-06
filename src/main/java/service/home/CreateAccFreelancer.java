package service.home;

import dao.client.ClientDAOImpl;
import dao.client.IClientDAO;
import dao.common.IUserDAO;
import dao.common.UserDAO;
import dao.freelancer.FreelancerDAO;
import dao.freelancer.IFreelancerDAO;
import dto.Freelancer;
import dto.User;

public class CreateAccFreelancer implements ICreateAccFreelancer{
    private IUserDAO userDAO = new UserDAO();
    private IFreelancerDAO freelancerDAO = new FreelancerDAO();
    public boolean registerFreelancer(User user, Freelancer freelancer) {
        boolean isSuccess = false;
        try {
            int userResult = userDAO.insertUser(user);
            int clientResult = freelancerDAO.insertFreelancer(freelancer);
            if (userResult > 0 && clientResult > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
