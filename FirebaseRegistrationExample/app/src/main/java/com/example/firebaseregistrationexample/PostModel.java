package com.example.firebaseregistrationexample;

public class PostModel {

    private String user_email;
    private String post_title;
    private String post_content;

    public PostModel() {}

    public PostModel(String user_email, String post_title, String post_content) {
        this.user_email = user_email;
        this.post_title = post_title;
        this.post_content = post_content;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }
}
