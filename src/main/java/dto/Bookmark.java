package dto;

public class Bookmark {
    private Long bookmarkId;
    private Long userId;
    private Long targetUserId;
    private String createdAt;

    public Bookmark(Long bookmarkId, Long userId, Long targetUserId, String createdAt) {
        this.bookmarkId = bookmarkId;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.createdAt = createdAt;
    }

    public Long getBookmarkId() { return bookmarkId; }
    public void setBookmarkId(Long bookmarkId) { this.bookmarkId = bookmarkId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTargetUserId() { return targetUserId; }
    public void setTargetUserId(Long targetUserId) { this.targetUserId = targetUserId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

}