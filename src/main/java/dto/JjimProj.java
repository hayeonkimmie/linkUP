package dto;

import java.sql.Date;

public class JjimProj {

    private Integer jjimId;                     // 찜 ID
    private Integer projectId;                  // 프로젝트 ID
    private String category;                    // 카테고리
    private String advertisementTitle;          // 공고 제목
    private String projectName;                 // 프로젝트명
    private String duration;                    // 기간
    private Date deadlineDate;                  // 마감일
    private String companyName;                 // 회사명
    private String qualification;               // 자격 요건
    private String workingMethod;               // 근무 방식
    private String preferentialConditions;      // 우대 조건
    private String projectDescription;          // 프로젝트 설명
    private String jobDetails;                  // 업무 상세
    private String workingEnvironment;          // 근무 환경
    private String workingHours;                // 근무 시간
    private String dDay;                        // 마감일 기준 D-Day 텍스트 (D-3, D-Day, 마감 등)

    // 기본 생성자
    public JjimProj() {}

    // Getter / Setter
    public Integer getJjimId() {
        return jjimId;
    }

    public void setJjimId(Integer jjimId) {
        this.jjimId = jjimId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getWorkingMethod() {
        return workingMethod;
    }

    public void setWorkingMethod(String workingMethod) {
        this.workingMethod = workingMethod;
    }

    public String getPreferentialConditions() {
        return preferentialConditions;
    }

    public void setPreferentialConditions(String preferentialConditions) {
        this.preferentialConditions = preferentialConditions;
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

    public String getdDay() {
        return dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    @Override
    public String toString() {
        return "JjimProj{" +
                "jjimId=" + jjimId +
                ", projectId=" + projectId +
                ", category='" + category + '\'' +
                ", advertisementTitle='" + advertisementTitle + '\'' +
                ", projectName='" + projectName + '\'' +
                ", duration='" + duration + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", companyName='" + companyName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", workingMethod='" + workingMethod + '\'' +
                ", preferentialConditions='" + preferentialConditions + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", workingEnvironment='" + workingEnvironment + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", dDay='" + dDay + '\'' +
                '}';
    }
}