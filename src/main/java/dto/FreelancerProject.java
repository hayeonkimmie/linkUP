package dto;

import java.sql.Date;

public class FreelancerProject { //구직자 기준 진행중/완료된 프로젝트
    private Integer projectId;        // 프로젝트 ID
    private String clientName;       // 회사명
    private String projectName;       // 프로젝트 명
    private String projectDuration;   // 프로젝트 기간 DB상으로는 계약시작일~계약종료일
    private String reqSkills;
    private String qualification;
    private String categories;   // 참여하고 있는 카테고리
    private String projectDescription;   // 프로젝트 소개
    private String totalBudget;   // 프로젝트 예산
    private String jobDetails;   // 프로젝트 상세 업무 내용
    private String workingMethod;
    private String workingEnvironment;
    private String workingHours;
    private String projectManager;    // 프로젝트 담당자
    private String managerPhone;      // 담당자 전화번호
    private Date deadlineDate;            // 마감일
    private String dDay;                  // D-Day 계산값 (ex: D-3, D-Day, +2)


    public FreelancerProject() {
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getReqSkills() {
        return reqSkills;
    }

    public void setReqSkills(String reqSkills) {
        this.reqSkills = reqSkills;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
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

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getdDay() {
        return dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    @Override
    public String toString() {
        return "FreelancerProject{" +
                "projectId=" + projectId +
                ", clientName='" + clientName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDuration='" + projectDuration + '\'' +
                ", reqSkills='" + reqSkills + '\'' +
                ", qualification='" + qualification + '\'' +
                ", categories='" + categories + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", totalBudget='" + totalBudget + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", workingMethod='" + workingMethod + '\'' +
                ", workingEnvironment='" + workingEnvironment + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", dDay='" + dDay + '\'' +
                '}';
    }
}
