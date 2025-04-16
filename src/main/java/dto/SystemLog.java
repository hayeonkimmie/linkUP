package dto;

public class SystemLog {
    private Long logId;
    private Long userId;
    private String action;
    private String ipAddress;
    private String createdAt;

    public SystemLog(Long logId, Long userId, String action, String ipAddress, String createdAt) {
        this.logId = logId;
        this.userId = userId;
        this.action = action;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
