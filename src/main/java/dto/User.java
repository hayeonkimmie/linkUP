package dto;

import java.sql.Date;

public class User {
    String userId;
    String name;
    String nickname;
    String email;
    String password;
    String phoneNum;
    String profileImg; // 이 부분 오타
    boolean acceptNoti;
    String token;
    Date registrationDate;
    Date withdrawalDate;
    boolean acceptConsent;
    String snsType;



    public User() {
        super();
    }

    public User(String userId, String name, String nickname, String email, String password, String phoneNum, String profilImg, boolean acceptNoti, String token, Date registrationDate, Date withdrawalDate, boolean acceptConsent, String snsType) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.profileImg = profileImg;
        this.acceptNoti = acceptNoti;
        this.token = token;
        this.registrationDate = registrationDate;
        this.withdrawalDate = withdrawalDate;
        this.acceptConsent = acceptConsent;
        this.snsType = snsType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", profilImg='" + profileImg + '\'' +
                '}';
    }
}
