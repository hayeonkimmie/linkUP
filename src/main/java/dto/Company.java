package dto;

import java.sql.Date;

public class Company {
    private int id;
    private String email;
    private String name;
    private Date joinDate;
    private String phone;
    private String type; // 구직자 or 구인자
    private Date foundedDate; // 회사 설립일
    private String ceo;
    private String industry;
    private String businessType;
    private String address;

    public Company(int id, String email, String name, Date joinDate, String phone, String type, Date foundedDate, String ceo, String industry, String businessType, String address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.joinDate = joinDate;
        this.phone = phone;
        this.type = type;
        this.foundedDate = foundedDate;
        this.ceo = ceo;
        this.industry = industry;
        this.businessType = businessType;
        this.address = address;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }


}
