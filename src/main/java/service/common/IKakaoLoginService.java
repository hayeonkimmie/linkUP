package service.common;

import dto.User;

public interface IKakaoLoginService {
    User kakaoLogin(String code, String role) throws Exception;
}
