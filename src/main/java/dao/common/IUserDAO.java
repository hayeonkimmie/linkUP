package dao.common;

import dto.User;

public interface IUserDAO {
    int insertUser(User user);

    User selectUserById(String id);

}
