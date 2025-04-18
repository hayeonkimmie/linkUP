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
    private String type;


    public Client() {}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", ceo='" + ceo + '\'' +
                ", companyRegNo='" + companyRegNo + '\'' +
                ", companyPhoneNumber='" + companyPhoneNumber + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyWebsiteUrl='" + companyWebsiteUrl + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                ", companyFaxNum='" + companyFaxNum + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
