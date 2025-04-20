package dto;
import java.time.LocalDate;
import java.util.Date;


public class Apply {
    private Integer applyId;
    private String freelancerId;
    private Date applyDate;
    private Date cancelDate;
    private boolean isApproved;
    private Date approvalChangeDate;
    private Integer subCategoryId; // 서브 카테고리 ID
    private String subCategoryName; // 서브 카테고리 이름
    private Integer projectId;                  // 프로젝트 ID
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

    public Apply(Integer applyId, String freelancerId, Date applyDate, Date cancelDate, boolean isApproved, Date approvalChangeDate, Integer subCategoryId, String subCategoryName, Integer projectId, String advertisementTitle, String projectName, String duration, Date deadlineDate, String companyName, String qualification, String workingMethod, String preferentialConditions, String projectDescription, String jobDetails, String workingEnvironment, String workingHours, String dDay) {
        this.applyId = applyId;
        this.freelancerId = freelancerId;
        this.applyDate = applyDate;
        this.cancelDate = cancelDate;
        this.isApproved = isApproved;
        this.approvalChangeDate = approvalChangeDate;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.projectId = projectId;
        this.advertisementTitle = advertisementTitle;
        this.projectName = projectName;
        this.duration = duration;
        this.deadlineDate = deadlineDate;
        this.companyName = companyName;
        this.qualification = qualification;
        this.workingMethod = workingMethod;
        this.preferentialConditions = preferentialConditions;
        this.projectDescription = projectDescription;
        this.jobDetails = jobDetails;
        this.workingEnvironment = workingEnvironment;
        this.workingHours = workingHours;
        this.dDay = dDay;
    }

    public Apply() {
        super();
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Date getApprovalChangeDate() {
        return approvalChangeDate;
    }

    public void setApprovalChangeDate(Date approvalChangeDate) {
        this.approvalChangeDate = approvalChangeDate;
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
        return "Apply{" +
                "applyId=" + applyId +
                ", freelancerId='" + freelancerId + '\'' +
                ", applyDate=" + applyDate +
                ", cancelDate=" + cancelDate +
                ", isApproved=" + isApproved +
                ", approvalChangeDate=" + approvalChangeDate +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", projectId=" + projectId +
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
