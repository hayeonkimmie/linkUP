package dto;

import java.sql.Date;

public class Project {
    private int projectId;
    private String clientId;
    private String advertisementTitle;
    private String projectName;
    private String thumbnail;
    int duration;
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
    private int subCategoryId;
    private String subCategoryName;
    private String categoryName;
    private String profileImg;
    private String email;

    public Project() {
    }

    public Project(int projectId, String clientId, String advertisementTitle, String projectName, String thumbnail, int duration, Date createdDate, Date deadlineDate, String projectDescription, String jobDetails, String workingMethod, String workingEnvironment, String workingHours, String qualification, String preferentialConditions, Date settleDay, String manager, String mphone, int subCategoryId, String subCategoryName, String categoryName, String profileImg, String email) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.advertisementTitle = advertisementTitle;
        this.projectName = projectName;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.createdDate = createdDate;
        this.deadlineDate = deadlineDate;
        this.projectDescription = projectDescription;
        this.jobDetails = jobDetails;
        this.workingMethod = workingMethod;
        this.workingEnvironment = workingEnvironment;
        this.workingHours = workingHours;
        this.qualification = qualification;
        this.preferentialConditions = preferentialConditions;
        this.settleDay = settleDay;
        this.manager = manager;
        this.mphone = mphone;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryName = categoryName;
        this.profileImg = profileImg;
        this.email = email;
    }

    public Project(String mphone, String manager, Date settleDay, String preferentialConditions, String qualification, String workingHours, String workingEnvironment, String workingMethod, String jobDetails, String projectDescription, Date deadlineDate, Date createdDate, int duration, String thumbnail, String projectName, String advertisementTitle, String clientId, int projectId) {
        this.mphone = mphone;
        this.manager = manager;
        this.settleDay = settleDay;
        this.preferentialConditions = preferentialConditions;
        this.qualification = qualification;
        this.workingHours = workingHours;
        this.workingEnvironment = workingEnvironment;
        this.workingMethod = workingMethod;
        this.jobDetails = jobDetails;
        this.projectDescription = projectDescription;
        this.deadlineDate = deadlineDate;
        this.createdDate = createdDate;
        this.duration = duration;
        this.thumbnail = thumbnail;
        this.projectName = projectName;
        this.advertisementTitle = advertisementTitle;
        this.clientId = clientId;
        this.projectId = projectId;
    }

    public Project(int projectId, String clientId, String advertisementTitle, String projectName, String thumbnail, int duration, String projectDescription, String jobDetails, String workingMethod, String workingEnvironment, String workingHours, String qualification, String preferentialConditions, Date deadlineDate) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.advertisementTitle = advertisementTitle;
        this.projectName = projectName;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.projectDescription = projectDescription;
        this.jobDetails = jobDetails;
        this.workingMethod = workingMethod;
        this.workingEnvironment = workingEnvironment;
        this.workingHours = workingHours;
        this.qualification = qualification;
        this.preferentialConditions = preferentialConditions;
        this.deadlineDate = deadlineDate;
    }

    public Project(int projectId, String clientId, String advertisementTitle, String projectName, String thumbnail, int duration, Date createdDate, Date deadlineDate, String projectDescription, String jobDetails, String workingMethod, String workingEnvironment, String workingHours, String qualification, String preferentialConditions, Date settleDay, String manager, String mphone, int subCategoryId, String subCategoryName, String categoryName, String jobPosition, String reqSkills, String wantedSkills, String memail, String profileImg, double avgStar) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.advertisementTitle = advertisementTitle;
        this.projectName = projectName;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.createdDate = createdDate;
        this.deadlineDate = deadlineDate;
        this.projectDescription = projectDescription;
        this.jobDetails = jobDetails;
        this.workingMethod = workingMethod;
        this.workingEnvironment = workingEnvironment;
        this.workingHours = workingHours;
        this.qualification = qualification;
        this.preferentialConditions = preferentialConditions;
        this.settleDay = settleDay;
        this.manager = manager;
        this.mphone = mphone;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryName = categoryName;
        this.jobPosition = jobPosition;
        this.reqSkills = reqSkills;
        this.wantedSkills = wantedSkills;
        this.memail = memail;
        this.profileImg = profileImg;
        this.avgStar = avgStar;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getReqSkills() {
        return reqSkills;
    }

    public void setReqSkills(String reqSkills) {
        this.reqSkills = reqSkills;
    }

    public String getWantedSkills() {
        return wantedSkills;
    }

    public void setWantedSkills(String wantedSkills) {
        this.wantedSkills = wantedSkills;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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


    @Override
    public String toString() {
        return "Project{" +
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
                ", categoryName='" + categoryName + '\'' +
                ", profileImg='" + profileImg + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}