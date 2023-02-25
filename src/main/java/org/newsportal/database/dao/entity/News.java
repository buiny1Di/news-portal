package org.newsportal.database.dao.entity;

public class News {
    private String id;
    private String title;
    private String content;
    private String userId;

    public News() {
    }

    public News(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public News(String id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
