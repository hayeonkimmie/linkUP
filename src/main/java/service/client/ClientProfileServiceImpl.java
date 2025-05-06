package service.client;

import dao.client.ClientProfileDAOImpl;
import dao.client.IClientProfileDAO;
import dto.ClientProfile;
import dto.ClientUserDetail;
import dto.ClientUserInfo;

import java.sql.Date;
import java.util.Map;

public class ClientProfileServiceImpl implements IClientProfileService {
    // 외부 접근 차단, 값 재할당 금지
    private final IClientProfileDAO clientProfileDAO; // 해당 서비스 클래스 안에서만 사용할 수 있도록 제한

    // 기본 생성자
    public ClientProfileServiceImpl() {
        clientProfileDAO = new ClientProfileDAOImpl();
    }

    @Override
    public ClientProfile getClientProfile(String userId) throws Exception {
        // DAO통해서 받아온 Map<String ,Object> 데이터를 ClientProfile DTO에 담아서 리턴
        // 1. DAO에서 정보 조회
        Map<String, Object> param = clientProfileDAO.selectClientProfile(userId);
        // DAO에서 받은 결과를 각 DTO필드에 맞게 담아줘야하므로 set사용

        // 2. ClientUserInfo 객체 만들기
        ClientUserInfo info = new ClientUserInfo();
        // map에서 값 꺼내기 (get 뒤에는 테이블 명의 컬럼과 정확하게 일치해야 함)
        info.setUserId((String) param.get("user_id")); // 유저 아이디
        info.setName((String) param.get("name")); // 아름
        info.setEmail((String) param.get("email")); // 인사담당자 이메일
        info.setPhoneNumber((String) param.get("phone_num")); // 담당자 휴대폰 번호

        // 3. ClientUserDetail 객체 만들기
        ClientUserDetail detail = new ClientUserDetail();
        detail.setName((String) param.get("company_name")); // 회사/기관명
        detail.setCompanyWebsiteUrl((String) param.get("company_website_url")); // 회사/기관 홈페이지
        detail.setRegistrationDate((Date) param.get("founded_date")); // 설립일자
        detail.setCompanyRegNo((String) param.get("company_reg_no")); // 사업자등록번호
        detail.setCompanyDescription((String) param.get("company_description")); // 회사 소개
        detail.setCompanyPhoneNumber((String) param.get("phone_num")); // 회사번호
        detail.setCompanyFaxNum((String) param.get("company_fax_num")); // 팩스번호

        // 위의 2번과 3번 DTO조합한 ClientProfile
        ClientProfile profile = new ClientProfile();
        profile.setInfo(info);
        profile.setDetail(detail);

        return profile;
    }

    @Override
    public int updateClientProfile(ClientProfile profile) throws Exception {
        return clientProfileDAO.updateClientProfile(profile);
    }

    @Override
    public boolean checkCurrentPassword(String clientId, String currentPw) throws Exception {
        String storedPw = clientProfileDAO.getPasswordByUserId(clientId);
        return storedPw != null && storedPw.equals(currentPw);
    }

    @Override
    public void updatePassword(String clientId, String newPw) throws Exception {
        clientProfileDAO.updatePassword(clientId, newPw);
    }
}


