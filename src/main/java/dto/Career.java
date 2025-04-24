package dto;

import java.sql.Date;

public class Career {
    private String freelancerId;
    private String companyName; // 회사명
    private String departmentName; // 부서명
    private String position; // 직급/직책
    private String jobTitle; // 직무
    private String jobDescription; // 담당업무
    private Integer salary;// 연봉
    private Date joinDate; // 입사일
    private Date resignDate; // 퇴사일

    public Career() {
        super();
    }
    public Career(String freelancerId, String companyName, String departmentName, String position, String jobTitle,
                  String jobDescription, Integer salary, Date joinDate, Date resignDate) {
        super();
        this.freelancerId = freelancerId;
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.position = position;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.joinDate = joinDate;
        this.resignDate = resignDate;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    @Override
    public String toString() {
        return "Career{" +
                "freelancerId='" + freelancerId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", position='" + position + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                ", resignDate=" + resignDate +
                '}';
    }
}
