package dto;

import java.sql.Date;

public class News {
    private int newsId;
    private String title;
    private String content;
    private Date createdAt;

    public News(){}
    public News(int newsId, String title, String content, Date createdAt) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
