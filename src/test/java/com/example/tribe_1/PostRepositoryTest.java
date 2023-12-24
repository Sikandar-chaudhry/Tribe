package com.example.tribe_1;

import com.example.tribe_1.user.Post;
import com.example.tribe_1.user.PostRepository;
import com.example.tribe_1.user.User;
import com.example.tribe_1.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PostRepositoryTest {
    @Autowired private PostRepository postRepo;
    @Autowired private UserRepository userRepo;

    @Test
    public void addPost(){
        Optional<User> userOptional = userRepo.findById(1);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = new Post();
            post.setAuthor(user);
            post.setPostText("Welcome to the Tribe App");
            user.addPost(post);
            // Save the changes to the database
            postRepo.save(post);
        } else {

        }
    }
}
