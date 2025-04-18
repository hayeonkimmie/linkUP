package dto;

public class RecruiterProfile {
    private Long profileId;
    private Long userId;
    private String companyName;
    private String companyNumber;
    private String introduction;
    private String createdAt;
    private String updatedAt;

    public RecruiterProfile(Long profileId, Long userId, String companyName, String companyNumber, String introduction, String createdAt, String updatedAt) {
        this.profileId = profileId;
        this.userId = userId;
        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.introduction = introduction;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
