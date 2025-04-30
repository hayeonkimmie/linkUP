package dto;

import java.sql.Date;

public class ProjectDetail {
    private Integer projectId;
    private String clientId;
    private String advertisementTitle;
    private String projectName;
    private String thumbnail;
    private Integer duration;
    private Date createdDate;
    private Date deadlineDate;
    private String projectDescription;
    private String jobDetails;
    private String workingMethod;
    private String workingEnvironment;
    private String workingHours;
    private String qualification;
    private String preferentialConditions;
    private Date settleDay;
    private String manager;
    private String mphone; // 이건 이미 카멜표기법에 맞음
    private Integer subCategoryId;
    private String subCategoryName;
    private String email;
    private String profileImg;

    public ProjectDetail() {
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public String getWorkingMethod() {
        return workingMethod;
    }

    public void setWorkingMethod(String workingMethod) {
        this.workingMethod = workingMethod;
    }

    public String getWorkingEnvironment() {
        return workingEnvironment;
    }

    public void setWorkingEnvironment(String workingEnvironment) {
        this.workingEnvironment = workingEnvironment;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPreferentialConditions() {
        return preferentialConditions;
    }

    public void setPreferentialConditions(String preferentialConditions) {
        this.preferentialConditions = preferentialConditions;
    }

    public Date getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(Date settleDay) {
        this.settleDay = settleDay;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProjectDetail{" +
                "projectId=" + projectId +
                ", clientId='" + clientId + '\'' +
                ", advertisementTitle='" + advertisementTitle + '\'' +
                ", projectName='" + projectName + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", duration=" + duration +
                ", createdDate=" + createdDate +
                ", deadlineDate=" + deadlineDate +
                ", projectDescription='" + projectDescription + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", workingMethod='" + workingMethod + '\'' +
                ", workingEnvironment='" + workingEnvironment + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", qualification='" + qualification + '\'' +
                ", preferentialConditions='" + preferentialConditions + '\'' +
                ", settleDay=" + settleDay +
                ", manager='" + manager + '\'' +
                ", mphone='" + mphone + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", email='" + email + '\'' +
                ", profileImg='" + profileImg + '\'' +
                '}';
    }
}
