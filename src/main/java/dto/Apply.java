package dto;
import java.time.LocalDate;
import java.util.Date;

public class Apply {
    private Integer applyId;
    private String freelancerId;
    private Date applyDate;
    private Date cancelDate;
    private boolean isApproved; // 승인여부
    String approved;
    private String category; // 카테고리 이름
    private String applyCategory; // 카테고리 이름
    private Integer projectId;                  // 프로젝트 ID
    private String advertisementTitle;          // 공고 제목
    private String projectName;                 // 프로젝트명
    private String projectFee;                 // 프로젝트명
    private String duration;                    // 기간
    private Date deadlineDate;                  // 마감일
    private String companyName;                 // 회사명
    private String workingMethod;               // 근무 방식
    private String projectDescription;          // 프로젝트 설명
    private String jobDetails;                  // 업무 상세
    private String workingEnvironment;          // 근무 환경
    private String applyStatus; // 지원상태
    private String dDay;                        // 마감일 기준 D-Day 텍스트 (D-3, D-Day, 마감 등)

    private Integer projectPayId;
    private String subCategoryName;


    public Apply() {
        super();
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }


    public Integer getProjectPayId() {
        return projectPayId;
    }

    public void setProjectPayId(Integer projectPayId) {
        this.projectPayId = projectPayId;
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

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        isApproved = isApproved;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public String getProjectFee() {
        return projectFee;
    }

    public void setProjectFee(String projectFee) {
        this.projectFee = projectFee;
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

    public String getWorkingMethod() {
        return workingMethod;
    }

    public void setWorkingMethod(String workingMethod) {
        this.workingMethod = workingMethod;
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

    public String getdDay() {
        return dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyCategory() {
        return applyCategory;
    }

    public void setApplyCategory(String applyCategory) {
        this.applyCategory = applyCategory;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "applyId=" + applyId +
                ", freelancerId='" + freelancerId + '\'' +
                ", applyDate=" + applyDate +
                ", cancelDate=" + cancelDate +
                ", isApproved=" + isApproved +
                ", approved='" + approved + '\'' +
                ", category='" + category + '\'' +
                ", applyCategory='" + applyCategory + '\'' +
                ", projectId=" + projectId +
                ", advertisementTitle='" + advertisementTitle + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectFee='" + projectFee + '\'' +
                ", duration='" + duration + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", companyName='" + companyName + '\'' +
                ", workingMethod='" + workingMethod + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", jobDetails='" + jobDetails + '\'' +
                ", workingEnvironment='" + workingEnvironment + '\'' +
                ", applyStatus='" + applyStatus + '\'' +
                ", dDay='" + dDay + '\'' +
                '}';
    }
}