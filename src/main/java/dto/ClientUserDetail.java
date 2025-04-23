package dto;

import java.sql.Date;

public class ClientUserDetail {
    private String id;
    private String name;               // 회사명
    private String nickname;           // 대표자명
    private String email;              // 대표 이메일
    private Date registrationDate;     // 설립일자
    private String companyDescription; // 업종
    private String companyRegNo;       // 사업자등록번호
    private String businessType;  // 업태
    private String companyAddress;     // 본사 주소
    private String companyPhoneNumber; // 대표 전화번호
    private String companyFaxNum;      // FAX
    private String ceo;
    private String companyWebsiteUrl; // 웹사이트

    public ClientUserDetail() {
    }

    public ClientUserDetail(String id, String name, String nickname, String email, Date registrationDate, String companyDescription, String companyRegNo, String businessType, String companyAddress, String companyPhoneNumber, String companyFaxNum, String ceo) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.registrationDate = registrationDate;
        this.companyDescription = companyDescription;
        this.companyRegNo = companyRegNo;
        this.businessType = businessType;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyFaxNum = companyFaxNum;
        this.ceo = ceo;
        this.companyWebsiteUrl = companyWebsiteUrl;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanyFaxNum() {
        return companyFaxNum;
    }

    public void setCompanyFaxNum(String companyFaxNum) {
        this.companyFaxNum = companyFaxNum;
    }

    public String getCompanyWebsiteUrl() {
        return companyWebsiteUrl;
    }

    public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
        this.companyWebsiteUrl = companyWebsiteUrl;
    }
}
