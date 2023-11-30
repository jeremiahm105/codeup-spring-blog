package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        private String email;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Post> posts;

        public User() {
        }

        //copy constructor alternative to cloning an object.
        //we create a new object using the current values of another. This will be used in order to fulfill the contract defined by the interfaces in the security package
        public User(User copy) {
            id = copy.id;
            email = copy.email;
            username = copy.username;
            password = copy.password;
            posts = copy.posts;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }

    }