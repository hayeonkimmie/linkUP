package service.freelancer;

public interface IUserService {
    boolean isNicknameAvailable(String nickname);
    boolean checkPassword(String userId, String inputPassword);
}
