package service.admin;

import dto.ClientUserDetail;
import dto.ClientUserInfo;

import java.util.List;

public interface IClientService {
    /**
     * 모든 클라이언트 정보를 가져오는 메서드
     * @return List<ClientUserInfo> - 클라이언트 정보 리스트
     * @throws Exception - SQL 예외 발생 시
     */
    List<ClientUserInfo> getAllClients() throws Exception;


    /**
     * Id와 일치하는 클라이언트 정보를 가져오는 메서드
     * @param userId
     * @return ClientUserDetail
     * @throws Exception
     */
    ClientUserDetail selectClientById(String userId) throws Exception;


    /**
     * 클라이언트 정보를 추가하는 메서드
     * @param clientUserInfo - 추가할 클라이언트 정보
     * @throws Exception - SQL 예외 발생 시
     */
    void addClient(ClientUserInfo clientUserInfo) throws Exception;

    /**
     * 클라이언트 정보를 수정하는 메서드
     * @param clientUserInfo - 수정할 클라이언트 정보
     * @throws Exception - SQL 예외 발생 시
     */
    void updateClient(ClientUserInfo clientUserInfo) throws Exception;

    /**
     * 클라이언트를 삭제하는 메서드
     * @param clientId - 삭제할 클라이언트 ID
     * @throws Exception - SQL 예외 발생 시
     */
    void deleteClient(int clientId) throws Exception;
}
