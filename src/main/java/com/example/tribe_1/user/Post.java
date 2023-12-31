package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.Stack;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    @Transient
    private Stack<Comment> commentStack = new Stack<>(50);

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
    public void addComment(Comment comment) {
        commentStack.push(comment);
    }

    public void removeComment(Comment comment) {
        commentStack.delete(comment);
        comment.setAuthor(null);
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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
    public List<Comment> getComments() {
        return commentStack.toList();
    }
}
