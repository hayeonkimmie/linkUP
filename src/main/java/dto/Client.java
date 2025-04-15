package dto;

import java.sql.Date;

public class Client {
    private Integer id; // AUTO_INCREMENT
    private String clientId; // 사용자 ID
    private String email; // 담당자 이메일
    private String name; // 회사명
    private String phone; // 담당자 전화번호
    private String type; // 구직자 or 구인자
    private Date foundedDate; // 회사 설립일
    private String ceo; // CEO 이름
    private String industry; // 업종
    private String businessType; // 업태
    private String address; // 회사 주소
    private String businessNumber; // 사업자등록번호
    private String fax; // 팩스번호

    public Client(Integer id, String clientId, String email, String name, String phone, String type, Date foundedDate, String ceo, String industry, String businessType, String address, String businessNumber, String fax) {
        this.id = id;
        this.clientId = clientId;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.foundedDate = foundedDate; // 회사 설립일
        this.ceo = ceo; // CEO 이름
        this.industry = industry; // 업종
        this.businessType = businessType; // 업태
        this.address = address; // 회사 주소
        this.businessNumber = businessNumber;
        this.fax = fax; // 팩스번호
    }

    public Client() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
