package dto;

public class Notification {
    private Long notificationId;
    private Long userId;
    private String content;
    private String type;
    private boolean isRead;
    private String sentAt;

    public Notification(Long notificationId, Long userId, String content, String type, boolean isRead, String sentAt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
        this.sentAt = sentAt;
    }

    public Long getNotificationId() { return notificationId; }
    public void setNotificationId(Long notificationId) { this.notificationId = notificationId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean getIsRead() { return isRead; }
    public void setIsRead(boolean isRead) { this.isRead = isRead; }

    public String getSentAt() { return sentAt; }
    public void setSentAt(String sentAt) { this.sentAt = sentAt; }
}
