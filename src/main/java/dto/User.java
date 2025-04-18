package dto;

import java.util.Date;

public class User {
    String userId;
    String name;
    String nickname;
    String email;
    String password;
    String phoneNum;
    String profilImg;
    boolean acceptNoti;
    String token;
    Date registrationDate;
    Date withdrawalDate;
    boolean acceptConsent;
    boolean isNaver;
    boolean isKakao;

    public User() {
        super();
    }

    public User(String userId, String nickname, String profilImg, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.profilImg = profilImg;
        this.password = password;
    }

    public User(String userId, String name, String nickname, String email, String password, String phoneNum, String profilImg, boolean acceptNoti, String token, Date registrationDate, Date withdrawalDate, boolean acceptConsent, boolean isNaver, boolean isKakao) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.profilImg = profilImg;
        this.acceptNoti = acceptNoti;
        this.token = token;
        this.registrationDate = registrationDate;
        this.withdrawalDate = withdrawalDate;
        this.acceptConsent = acceptConsent;
        this.isNaver = isNaver;
        this.isKakao = isKakao;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProfilImg() {
        return profilImg;
    }

    public void setProfilImg(String profilImg) {
        this.profilImg = profilImg;
    }

    public boolean isAcceptNoti() {
        return acceptNoti;
    }

    public void setAcceptNoti(boolean acceptNoti) {
        this.acceptNoti = acceptNoti;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public boolean isAcceptConsent() {
        return acceptConsent;
    }

    public void setAcceptConsent(boolean acceptConsent) {
        this.acceptConsent = acceptConsent;
    }

    public boolean isNaver() {
        return isNaver;
    }

    public void setNaver(boolean naver) {
        isNaver = naver;
    }

    public boolean isKakao() {
        return isKakao;
    }

    public void setKakao(boolean kakao) {
        isKakao = kakao;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", profilImg='" + profilImg + '\'' +
                '}';
    }
}
