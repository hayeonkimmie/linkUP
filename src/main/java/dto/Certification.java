package dto;

public class Certification {
    private Long certificationId;
    private Long freelancerId;
    private String name;
    private String organization;
    private String acquiredAt;

    public Certification(Long certificationId, Long freelancerId, String name, String organization, String acquiredAt) {
        this.certificationId = certificationId;
        this.freelancerId = freelancerId;
        this.name = name;
        this.organization = organization;
        this.acquiredAt = acquiredAt;
    }

    public Long getCertificationId() { return certificationId; }
    public void setCertificationId(Long certificationId) { this.certificationId = certificationId; }

    public Long getFreelancerId() { return freelancerId; }
    public void setFreelancerId(Long freelancerId) { this.freelancerId = freelancerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public String getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(String acquiredAt) { this.acquiredAt = acquiredAt; }
}
