package dto;

import io.grpc.internal.JsonUtil;

public class Freelancer {
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
}
