package com.example.tribe_1;

import com.example.tribe_1.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CommentRepositoryTest {

    @Autowired
    private PostRepository postRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private CommentRepository commentRepo;

    @Test
    public void addComment(){
        Optional<Post> postOptional = postRepo.findById(1L);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            User user = post.getAuthor();
            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setPost(post);
            comment.setCommentText("Second comment");
            post.addComment(comment);
            // Save the changes to the database
            commentRepo.save(comment);
        } else {

        }
    }

}
