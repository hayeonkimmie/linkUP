package dto;

public class NotificationSetting {
    private Long settingId;
    private Long userId;
    private Boolean allowEmail;
    private Boolean allowSMS;
    private Boolean allowPush;

    public NotificationSetting(Long settingId, Long userId, Boolean allowEmail, Boolean allowSMS, Boolean allowPush) {
        this.settingId = settingId;
        this.userId = userId;
        this.allowEmail = allowEmail;
        this.allowSMS = allowSMS;
        this.allowPush = allowPush;
    }

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getAllowEmail() {
        return allowEmail;
    }

    public void setAllowEmail(Boolean allowEmail) {
        this.allowEmail = allowEmail;
    }

    public Boolean getAllowSMS() {
        return allowSMS;
    }

    public void setAllowSMS(Boolean allowSMS) {
        this.allowSMS = allowSMS;
    }

    public Boolean getAllowPush() {
        return allowPush;
    }

    public void setAllowPush(Boolean allowPush) {
        this.allowPush = allowPush;
    }
}
