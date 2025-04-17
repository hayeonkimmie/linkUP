package dto;

public class Experience {
    private Long experienceId;
    private Long freelancerId;
    private String company;
    private String role;
    private String startDate;
    private String endDate;

    public Experience(Long experienceId, Long freelancerId, String company, String role, String startDate, String endDate) {
        this.experienceId = experienceId;
        this.freelancerId = freelancerId;
        this.company = company;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getExperienceId() { return experienceId; }
    public void setExperienceId(Long experienceId) { this.experienceId = experienceId; }

    public Long getFreelancerId() { return freelancerId; }
    public void setFreelancerId(Long freelancerId) { this.freelancerId = freelancerId; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}
