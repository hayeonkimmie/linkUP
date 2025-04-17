package dto;

public class Education {
    private Long educationId;
    private Long freelancerId;
    private String school;
    private String major;
    private String degree;
    private String startDate;
    private String endDate;

    public Education(Long educationId, Long freelancerId, String school, String major, String degree, String startDate, String endDate) {
        this.educationId = educationId;
        this.freelancerId = freelancerId;
        this.school = school;
        this.major = major;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getEducationId() { return educationId; }
    public void setEducationId(Long educationId) { this.educationId = educationId; }

    public Long getFreelancerId() { return freelancerId; }
    public void setFreelancerId(Long freelancerId) { this.freelancerId = freelancerId; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
