package com.example.tribe_1.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;
    @Column(nullable = false, length = 1000)
    private String postText;
    @Column(nullable = false)
    private LocalDate postDate;
    @ManyToOne // corrected annotation
    @JoinColumn(name = "userId") // specify the foreign key column
    private User author;

    @PrePersist
    protected void onCreate() {
        postDate = LocalDate.now();
    }
    public Post(String postText, User author) {
        this.postText = postText;
        this.author = author;
    }

    public Post() {
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
