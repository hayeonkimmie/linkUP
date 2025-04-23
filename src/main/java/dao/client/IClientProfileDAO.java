package dao.client;

import dto.*;

import java.util.List;
import java.util.Map;

public interface IClientProfileDAO {
    // 프로필 조회
    Map<String, Object> selectClientProfile(String userId) throws Exception;

    // 프로필 수정 (정수로 반환받아서 해당 행을 수정)
    int updateClientProfile(ClientProfile profile) throws Exception;
    String getPasswordByUserId(String userId) throws Exception;
    void updatePassword(String userId, String newPassword) throws Exception;
}
