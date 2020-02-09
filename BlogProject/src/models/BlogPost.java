package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BlogPost {

    private int id;
    private int userId;
    private String title;
    private String text;
    private String date;

    private ArrayList<Comment> comments;

    public BlogPost(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }
    public BlogPost(){}


    public BlogPost(int id, int userId, String title, String text, String date) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.date = date;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
