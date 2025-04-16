package dto;

import java.sql.Date;

public class User {
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

    public User() {
        super();
    }

    public User(String user_id, String name, String nickname, String password, String email, String profile_img, boolean accept_noti, String token, Date withdrawal_date, String detailAddress, String profileImage, boolean accept_consent) {
        this.user_id = user_id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.profile_img = profile_img;
        this.accept_noti = accept_noti;
        this.token = token;
        this.withdrawal_date = withdrawal_date;
        this.profileImage = profileImage;
        this.accept_consent = accept_consent;
    }

    public User(String user_id, String name, String nickname, String password, String email, String profile_img, boolean accept_noti, String profileImage) {
        this.user_id = user_id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.profile_img = profile_img;
        this.accept_noti = accept_noti;
        this.profileImage = profileImage;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public boolean isAccept_noti() {
        return accept_noti;
    }

    public void setAccept_noti(boolean accept_noti) {
        this.accept_noti = accept_noti;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getWithdrawal_date() {
        return withdrawal_date;
    }

    public void setWithdrawal_date(Date withdrawal_date) {
        this.withdrawal_date = withdrawal_date;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isAccept_consent() {
        return accept_consent;
    }

    public void setAccept_consent(boolean accept_consent) {
        this.accept_consent = accept_consent;
    }
}
