package com.example.tribe_1.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    @Column(nullable = false, length = 1000)
    private String commentText;
    @Column(nullable = false)
    private LocalDate commentDate;
    @PrePersist
    protected void onCreate() {
        commentDate = LocalDate.now();
    }

    @ManyToOne // corrected annotation
    @JoinColumn(name = "postId") // specify the foreign key column
    private Post post;

    @ManyToOne // Assuming a Many-to-One relationship between Comment and User
    @JoinColumn(name = "userId") // Specify the appropriate foreign key column
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
