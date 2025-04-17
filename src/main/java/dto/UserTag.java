package dto;

public class UserTag {
    private Long userTagId;
    private Long userId;
    private Long tagId;

    public UserTag(Long userTagId, Long userId, Long tagId) {
        this.userTagId = userTagId;
        this.userId = userId;
        this.tagId = tagId;
    }

    public Long getUserTagId() { return userTagId; }
    public void setUserTagId(Long userTagId) { this.userTagId = userTagId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTagId() { return tagId; }
    public void setTagId(Long tagId) { this.tagId = tagId; }
}
