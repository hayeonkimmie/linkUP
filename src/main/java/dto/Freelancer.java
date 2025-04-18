package dto;

import io.grpc.internal.JsonUtil;

public class Freelancer {
    private String freelancerId;
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


    public Freelancer() {}

    /**
     * /admin/client_list.jsp 페이지에서 사용되는 생성자<br>
     * @param freelancerId 사용자 ID
     * @param name 이름
     * @param email 이메일
     * @param registrationDate 가입일
     * @param phoneNum 연락처
     * @param type 사용자 구분 (구인자/구직자)
     */
    public Freelancer(String freelancerId, String name, String email, Date registrationDate, String phoneNum, String type) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
        this.phoneNum = phoneNum;
        this.type = type;
    }

    public Freelancer(String freelancerId, String name, String nickname, String email, String phoneNum,
                      Date registrationDate, String academic, String skill, String desiredSalary,
                      String desiredLocation, Boolean isResident, String introduction, String license,
                      String attachment, String externalUrl, String otherRequests, String type, String address) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.registrationDate = registrationDate;
        this.academic = academic;
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

    public Freelancer(String freelancerId, String category, String skill, String address, String academic, String introduction, String license, String bank, String accountNum, boolean isNegotiable, boolean isResident, int desiredSalary, String desiredLocation, String otherRequest, String attachment, String externalUrl) {
        this.freelancerId = freelancerId;
        this.category = category;
        this.skill = skill;
        this.address = address;
        this.academic = academic;
        this.introduction = introduction;
        this.license = license;
        this.bank = bank;
        this.accountNum = accountNum;
        this.isNegotiable = isNegotiable;
        this.isResident = isResident;
        this.desiredSalary = desiredSalary;
        this.desiredLocation = desiredLocation;
        this.otherRequest = otherRequest;
        this.attachment = attachment;
        this.externalUrl = externalUrl;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public boolean isNegotiable() {
        return isNegotiable;
    }

    public void setNegotiable(boolean negotiable) {
        isNegotiable = negotiable;
    }

    public boolean isResident() {
        return isResident;
    }

    public void setResident(boolean resident) {
        isResident = resident;
    }

    public int getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(int desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getUserId() { return freelancerId; }
    public void setUserId(String userId) { this.freelancerId = userId; }

    public void setDesiredLocation(String desiredLocation) {
        this.desiredLocation = desiredLocation;
    }

    public String getOtherRequest() {
        return otherRequest;
    }

    public void setOtherRequest(String otherRequest) {
        this.otherRequest = otherRequest;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }
}
