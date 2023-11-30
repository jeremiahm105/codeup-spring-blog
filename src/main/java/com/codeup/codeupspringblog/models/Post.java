package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String title;
    @Column (nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    constructors
    public Post() {
    }

    public Post(long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public User getOwner() {
//    }
//post class instance and potential use
//    public static void main(String[] args) {
////        Post myPost = new Post();
////
////        myPost.setTitle("");
////        myPost.setBody("");
//    }
}
