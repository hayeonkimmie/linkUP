package dto;

import java.sql.Date;

public class News {
    int news_id;
    String title;
    String content;
    Date created_at;

    public News(){}
    public News(int news_id, String title, String content, Date created_at) {
        this.news_id = news_id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
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






