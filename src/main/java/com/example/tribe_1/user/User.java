package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.Stack;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false, unique = true, length = 25)
    private String username;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 45, nullable = false, name = "first_name")
    private String first_name;
    @Column(length = 45, nullable = false, name = "last_name")
    private String last_name;
    // Add other relevant user attributes

    @Transient
    private Stack<Post> posts = new Stack<>(50);
    private boolean isAuthenticated = false;

    public User(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public void addPost(Post post) {
        posts.push(post);
    }

    public void removePost(Post post) {
        posts.delete(post);
        post.setAuthor(null);
    }


    public void setPosts(Stack<Post> posts) {
        this.posts = posts;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
