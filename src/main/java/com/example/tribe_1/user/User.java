package com.example.tribe_1.user;

public class User {
    private long userId;
    private String username;
    private String email;
    private String password;
    // Add other relevant user attributes

    public User(long userId,String email, String username, String password) {
        this.email = email;
        this.userId = userId;
        this.username = username;
        this.password = password;
        // Initialize other attributes as needed
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
}
