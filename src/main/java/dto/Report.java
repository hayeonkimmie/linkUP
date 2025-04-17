package dto;

public class Report {
    private Long reportId;
    private Long reporterId;
    private Long targetId;
    private String reason;
    private String createdAt;

    public Report(Long reportId, Long reporterId, Long targetId, String reason, String createdAt) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.targetId = targetId;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }

    public Long getReporterId() { return reporterId; }
    public void setReporterId(Long reporterId) { this.reporterId = reporterId; }

    public Long getTargetId() { return targetId; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
