package dto;

import java.sql.Date;

public class FreeLancer {
    private String clientId;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phoneNum;
    private String profileImg;
    private Boolean acceptNoti;
    private String token;
    private Date withdrawalDate;
    private Date registrationDate;
    private Boolean acceptConsent;
    private Integer rolesId;
    private String skill;
    private String address;
    private String academic;
    private String introduction;
    private String license;
    private String desiredSalary;
    private String bank;
    private String accountNum;
    private Boolean isResident;
    private String desiredLocation;
    private String otherRequests;
    private String attachment;
    private String externalUrl;
    private String type;

    public FreeLancer() {}

    /**
     * /admin/client_list.jsp 페이지에서 사용되는 생성자<br>
     * @param clientId 사용자 ID
     * @param name 이름
     * @param email 이메일
     * @param registrationDate 가입일
     * @param phoneNum 연락처
     * @param type 사용자 구분 (구인자/구직자)
     */
    public FreeLancer(String clientId, String name, String email, Date registrationDate, String phoneNum, String type) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
        this.phoneNum = phoneNum;
        this.type = type;
    }



    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getResident() {
        return isResident;
    }

    public void setResident(Boolean resident) {
        isResident = resident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() { return clientId; }
    public void setUserId(String userId) { this.clientId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getProfileImg() { return profileImg; }
    public void setProfileImg(String profileImg) { this.profileImg = profileImg; }

    public Boolean getAcceptNoti() { return acceptNoti; }
    public void setAcceptNoti(Boolean acceptNoti) { this.acceptNoti = acceptNoti; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Date getWithdrawalDate() { return withdrawalDate; }
    public void setWithdrawalDate(Date withdrawalDate) { this.withdrawalDate = withdrawalDate; }

    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public Boolean getAcceptConsent() { return acceptConsent; }
    public void setAcceptConsent(Boolean acceptConsent) { this.acceptConsent = acceptConsent; }

    public Integer getRolesId() { return rolesId; }
    public void setRolesId(Integer rolesId) { this.rolesId = rolesId; }

    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAcademic() { return academic; }
    public void setAcademic(String academic) { this.academic = academic; }

    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public String getDesiredSalary() { return desiredSalary; }
    public void setDesiredSalary(String desiredSalary) { this.desiredSalary = desiredSalary; }

    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }

    public String getAccountNum() { return accountNum; }
    public void setAccountNum(String accountNum) { this.accountNum = accountNum; }

    public Boolean getIsResident() { return isResident; }
    public void setIsResident(Boolean isResident) { this.isResident = isResident; }

    public String getDesiredLocation() { return desiredLocation; }
    public void setDesiredLocation(String desiredLocation) { this.desiredLocation = desiredLocation; }

    public String getOtherRequests() { return otherRequests; }
    public void setOtherRequests(String otherRequests) { this.otherRequests = otherRequests; }

    public String getAttachment() { return attachment; }
    public void setAttachment(String attachment) { this.attachment = attachment; }

    public String getExternalUrl() { return externalUrl; }
    public void setExternalUrl(String externalUrl) { this.externalUrl = externalUrl; }
}
