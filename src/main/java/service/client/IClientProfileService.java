package service.client;

import dto.ClientProfile;

public interface IClientProfileService {
    ClientProfile getClientProfile(String userId) throws Exception; //프로필 조회
    int updateClientProfile(ClientProfile profile) throws Exception;// 프로필 수정
    boolean checkCurrentPassword(String clientId, String currentPw) throws Exception; // 비밀번호 일치여부 조회
    void updatePassword(String clientId, String newPw) throws Exception; // 비밀번호 변경
}
