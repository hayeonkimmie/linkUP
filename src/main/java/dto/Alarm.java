package dto;

public class Alarm {

    Integer alarm_id;
    String user_id;
    String title;
    String content;
    Boolean confirm;

    public Alarm(Integer alarm_id, String user_id, String title, String content, Boolean confirm) {
        this.alarm_id = alarm_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.confirm = confirm;
    }

    public Integer getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Integer alarm_id) {
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

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }
}
