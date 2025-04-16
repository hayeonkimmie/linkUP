package dto;

import java.sql.Date;

public class Freelancer {
    String freelancer_id;// 프리랜서 ID=유저아이디
    String skill;// 기술
    String address;// 주소
    String academic;// 학력
    String introduction;// 자기소개
    String license;// 자격증
    String bank;// 은행명
    String account_num;// 계좌번호
    boolean is_resident;// 상주 가능 여부
    boolean is_negotiable;// 협의 가능 여부
    String desired_location;// 희망 근무지
    Integer desired_salary;// 희망 급여
    String other_requests;// 기타 요청사항
    String attachment;// 첨부파일
    String external_url; // 외부 링크
    String user_id;
    String name;
    String nickname;
    String password;
    String email;
    String profile_img;
    boolean accept_noti; // 알림 수신 동의 여부
    String token;
    Date withdrawal_date;
    String profileImage;
    boolean accept_consent;// 개인정보 수집 및 이용 동의 여부


    public Freelancer() {
        super();
    }
}