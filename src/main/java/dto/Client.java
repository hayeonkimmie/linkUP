package dto;

import java.util.Date;

public class Client {
    private String clientId;
    private String companyRegNo;
    private String companyName;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyWebsiteUrl;
    private String companyDescription;
    private String businessType;
    private Date foundedDate;
    private String companyFaxNum;
    private String ceo;

    public Client(String clientId, String companyRegNo, String companyName, String companyPhoneNumber, String companyAddress, String companyWebsiteUrl, String companyDescription, String businessType, Date foundedDate, String companyFaxNum, String ceo) {
        this.clientId = clientId;
        this.companyRegNo = companyRegNo;
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.companyWebsiteUrl = companyWebsiteUrl;
        this.companyDescription = companyDescription;
        this.businessType = businessType;
        this.foundedDate = foundedDate;
        this.companyFaxNum = companyFaxNum;
        this.ceo = ceo;
    }

    public Client() {

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }



    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsiteUrl() {
        return companyWebsiteUrl;
    }

    public void setCompanyWebsiteUrl(String companyWebsiteUrl) {
        this.companyWebsiteUrl = companyWebsiteUrl;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getCompanyFaxNum() {
        return companyFaxNum;
    }

    public void setCompanyFaxNum(String companyFaxNum) {
        this.companyFaxNum = companyFaxNum;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
}
