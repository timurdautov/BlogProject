package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {

    private int id;
    private int user_id;
    private int post_id;
    private String content;
    private String post_date;

    public Comment(int id, int user_id, int post_id, String content, String post_date) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
        this.post_date = post_date;
    }

    public Comment() { }

    public Comment(int user_id, int post_id, String content) {

        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        post_date = dtf.format(now);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }
}
