package dao.freelancer;

public interface IUserDAO {
    int countNickname(String nickname);
    String getPasswordById(String userId);
}
