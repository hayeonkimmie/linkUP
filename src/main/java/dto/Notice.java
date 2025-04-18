package dto;

import java.sql.Date;

public class Notice {
    int notice_id;
    String title;
    String content;
    Date created_at;

    public Notice() {}

    public Notice(int notice_id, String title, String content, Date created_at) {
        this.notice_id = notice_id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }


    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
