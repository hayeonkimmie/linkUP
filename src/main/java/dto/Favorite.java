package dto;

public class Favorite {
    private Long favoriteId;
    private Long userId;
    private Long targetUserId;
    private String createdAt;


    public Favorite(Long favoriteId, Long userId, Long targetUserId, String createdAt) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.createdAt = createdAt;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
