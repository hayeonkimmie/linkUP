package dto;

public class Alarm {
    private String alarmId;
    private String userId;
    private String title;
    private String content;
    private boolean confirm;

    public Alarm(String alarmId, String userId, String title, String content, boolean confirm) {
        this.alarmId = alarmId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.confirm = confirm;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
