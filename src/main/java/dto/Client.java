package dto;

public class Client {
    private String clientId;
    private String ceo;
    private String companyRegNo;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyWebsiteUrl;
    private String companyDescription;
    private String companyFaxNum;

    public Client(String clientId, String ceo, String companyRegNo, String companyPhoneNumber, String companyAddress, String companyWebsiteUrl, String companyDescription, String companyFaxNum) {
        this.clientId = clientId;
        this.ceo = ceo;
        this.companyRegNo = companyRegNo;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.companyWebsiteUrl = companyWebsiteUrl;
        this.companyDescription = companyDescription;
        this.companyFaxNum = companyFaxNum;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
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

    public String getCompanyFaxNum() {
        return companyFaxNum;
    }

    public void setCompanyFaxNum(String companyFaxNum) {
        this.companyFaxNum = companyFaxNum;
    }
}
