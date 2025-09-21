package com.hacktober.blog.blog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blog {

    private String username;
    private String title;
    private String content;

    // likes: { "count": int, "likedBy": [username strings] }
    private Map<String, Object> likes;

    // comments: array of maps { "username": ..., "comment": ... }
    private List<Map<String, String>> comments;

    public Blog() {
        this.likes = new HashMap<>();
        this.likes.put("count", 0);
        this.likes.put("likedBy", new ArrayList<String>());
        this.comments = new ArrayList<>();
    }

    public Blog(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.likes = new HashMap<>();
        this.likes.put("count", 0);
        this.likes.put("likedBy", new ArrayList<String>());
        this.comments = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Map<String, Object> getLikes() { return likes; }
    public void setLikes(Map<String, Object> likes) { this.likes = likes; }

    public List<Map<String, String>> getComments() { return comments; }
    public void setComments(List<Map<String, String>> comments) { this.comments = comments; }
}
