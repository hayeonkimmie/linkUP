package service.common;

import dto.User;

public interface IKakaoLoginService {
    User kakaoLogin(String code) throws Exception;
}
