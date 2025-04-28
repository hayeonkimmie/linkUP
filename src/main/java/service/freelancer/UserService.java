package service.freelancer;

import dao.freelancer.IUserDAO;
import dao.freelancer.UserDAO;

public class UserService implements IUserService {
    private IUserDAO userDAO;
    public UserService() {
        super();
        userDAO = new UserDAO();
    }
    @Override
    public boolean isNicknameAvailable(String nickname) {
        return userDAO.countNickname(nickname) == 0;
    }

    @Override
    public boolean checkPassword(String userId, String inputPassword) {
        String storedPassword = userDAO.getPasswordById(userId);
        return storedPassword != null && storedPassword.equals(inputPassword); // ※ 해시 적용 시 변경
    }
}
