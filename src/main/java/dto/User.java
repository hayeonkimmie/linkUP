package dto;

import java.util.Date;

public class User {
        String user_id;
        String name;
        String nickname;
        String email;
        String password;
        String phone_num;
        String profile_img;
        boolean accept_noti;
        String token;
        Date registration_date;
        Date withdrawal_date;
        boolean accept_consent;

        public User(String user_id, String name, String nickname, String email, String password, String phone_num, String profile_img, boolean accept_noti, String token, Date registration_date, Date withdrawal_date, boolean accept_consent) {
            this.user_id = user_id;
            this.name = name;
            this.nickname = nickname;
            this.email = email;
            this.password = password;
            this.phone_num = phone_num;
            this.profile_img = profile_img;
            this.accept_noti = accept_noti;
            this.token = token;
            this.registration_date = registration_date;
            this.withdrawal_date = withdrawal_date;
            this.accept_consent = accept_consent;
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

        public String getPhone_num() {
            return phone_num;
        }

        public void setPhone_num(String phone_num) {
            this.phone_num = phone_num;
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

        public Date getRegistration_date() {
            return registration_date;
        }

        public void setRegistration_date(Date registration_date) {
            this.registration_date = registration_date;
        }

        public Date getWithdrawal_date() {
            return withdrawal_date;
        }

        public void setWithdrawal_date(Date withdrawal_date) {
            this.withdrawal_date = withdrawal_date;
        }

        public boolean getAccept_consent() {
            return accept_consent;
        }

        public void setAccept_consent(boolean accept_consent) {
            this.accept_consent = accept_consent;
        }
    }