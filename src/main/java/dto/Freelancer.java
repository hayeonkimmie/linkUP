package dto;

import io.grpc.internal.JsonUtil;

import java.sql.Date;

public class Freelancer {

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

    String freelancerId;
    String category;
    String skill;
    String address;
    String academic;
    String introduction;
    String license;
    String bank;
    String accountNum;
    boolean isNegotiable;
    boolean isResident;
    int desiredSalary;
    String desiredLocation;
    String otherRequest;
    String attachment;
    String externalUrl;

    public Freelancer() {
        super();
    }

    public Freelancer(String freelancerId, String bank, String accountNum) {
        this.freelancerId = freelancerId;
        this.bank = bank;
        this.accountNum = accountNum;
    }

}
