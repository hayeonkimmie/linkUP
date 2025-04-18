package dto;

public class Alarm {
    private String alarm_id;
    private String user_id;
    private String title;
    private String content;
    private boolean confirm;

    public Alarm(String alarm_id, String user_id, String title, String content, boolean confirm) {
        this.alarm_id = alarm_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.confirm = confirm;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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