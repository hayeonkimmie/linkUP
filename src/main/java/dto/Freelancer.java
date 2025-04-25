package dto;

import java.sql.Date;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Freelancer {
    String freelancerId;
    String name;
    String nickname;
    String email;
    String password;
    String phoneNum;
    String profileImg;
    boolean acceptNoti;
    String token;
    Date registrationDate;
    Date withdrawalDate;
    boolean acceptConsent;
    private int subCategoryId;
    private String subCategoryName;
    private int categoryId;
    private String categoryName;
    private double avgStar; // ⭐ 평균 별점

    // User Type 04.18 15:02 추가
    String type;
    String category;
    String skill;
    String[] skillList;
    String address;
    List<Academic> academicList;
    String academic;
    String introduction;
    List<License> licenseList;
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
    String[] externalUrlList;
    String[] attachmentList;

    List<Portfolio> portfolioInfoList;
    double averageScore; //평점
    int projectCount; //프로젝트 수


    public double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(double avgStar) {
        this.avgStar = avgStar;
    }

    public Freelancer() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
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

    public String[] getSkillList() {
        return skillList;
    }

    public void setSkillList(String[] skillList) {
        this.skillList = skillList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
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

    public boolean getIsNegotiable() {
        return isNegotiable;
    }

    public void setIsNegotiable(boolean negotiable) {
        isNegotiable = negotiable;
    }

    public boolean getIsResident() {
        return isResident;
    }

    public void setIsResident(boolean resident) {
        isResident = resident;
    }

    public int getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(int desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getDesiredLocation() {
        return desiredLocation;
    }

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

    public String[] getExternalUrlList() {
        return externalUrlList;
    }

    public void setExternalUrlList(String[] externalUrlList) {
        this.externalUrlList = externalUrlList;
    }

    public String[] getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(String[] attachmentList) {
        this.attachmentList = attachmentList;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }
    public List<Portfolio> getPortfolioInfoList() {
        return portfolioInfoList;
    }
    public void setPortfolioInfoList(List<Portfolio> portfolioInfoList) {
        this.portfolioInfoList = portfolioInfoList;
    }


    public List<Academic> getAcademicList() {
        return academicList;
    }

    public void setAcademicList(List<Academic> academicList) {
        this.academicList = academicList;
    }

    public List<License> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<License> licenseList) {
        this.licenseList = licenseList;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public String toString() {
        return "Freelancer{" +
                "freelancerId='" + freelancerId + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", acceptNoti=" + acceptNoti +
                ", token='" + token + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", acceptConsent=" + acceptConsent +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", skill='" + skill + '\'' +
                ", skillList=" + skillList +
                ", address='" + address + '\'' +
                ", academicList=" + academicList +
                ", academic='" + academic + '\'' +
                ", introduction='" + introduction + '\'' +
                ", licenseList=" + licenseList +
                ", license='" + license + '\'' +
                ", bank='" + bank + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", isNegotiable=" + isNegotiable +
                ", isResident=" + isResident +
                ", desiredSalary=" + desiredSalary +
                ", desiredLocation='" + desiredLocation + '\'' +
                ", otherRequest='" + otherRequest + '\'' +
                ", attachment='" + attachment + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", portfolioInfoList=" + portfolioInfoList +
                ", averageScore=" + averageScore +
                ", projectCount=" + projectCount +
                '}';
    }
}
