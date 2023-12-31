package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.LinkedList;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired private UserService service;
    @Autowired private PostRepository postRepo;
    @Autowired private CommentRepository commentRepo;

    @GetMapping("/users")
    public String showUserList(Model model){
        LinkedList<User> users = service.listAll();
        model.addAttribute("listUsers",users);
        return "userDisplay";
    }
    @PostMapping("/login")
    public String performLogin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        if (service.user_is_authenticated(email, password)) {
            // Set some session attribute to indicate that the user is authenticated
            User user = service.getUserByEmailAndPassword(email, password);
            session.setAttribute("authenticatedUser", email);
            session.setAttribute("user", user);
            return "redirect:/newsfeed";
        } else {
            // Handle unsuccessful login (redirect back to the login page with an error message)
            return "redirect:/login?error=true";
        }
    }


    @GetMapping("/newsfeed")
    public String newsfeed(Model model, HttpSession session) {
        // Check if the user is authenticated (you need to implement your authentication logic)
        if (!isUserAuthenticated(session)) {
            return "redirect:/signin";
        }
        model.addAttribute("post", new Post());
        String userEmail = (String) session.getAttribute("authenticatedUser");
        User user = (User) session.getAttribute("user");
        List<Post> posts = service.getPostsByUserId(user.getUserId());
        model.addAttribute("userPosts", posts);
        model.addAttribute("username", userEmail);
        model.addAttribute("user", user);
        model.addAttribute("comment", new Comment());
        return "newsfeed";
    }
    //Sign out
    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the sign-in page
        return "redirect:/signin";
    }

    @PostMapping ("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes){
        service.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been registered successfully");
        //Redirecting
        return "redirect:/signin";
    }

    // Method to check if the user is authenticated
    private boolean isUserAuthenticated(HttpSession session) {
        // Check if the "authenticatedUser" attribute exists in the session
        return session.getAttribute("authenticatedUser") != null;
    }


    @PostMapping ("/user/post")
    public String newPost(Post post, RedirectAttributes redirectAttributes,HttpSession session){

        User user = (User) session.getAttribute("user");
        post.setAuthor(user);
        user.addPost(post);
        service.save(user);
        postRepo.save(post);
        redirectAttributes.addFlashAttribute("message", "Post uploaded successfully.");
        //Redirecting
        return "redirect:/newsfeed";
    }
    @PostMapping("/user/comment")
    public String newComment(@ModelAttribute Comment comment, @RequestParam("postId") Long postId, RedirectAttributes redirectAttributes, HttpSession session) {

        User user = (User) session.getAttribute("user");

        // Load the post from the database
        Post post = postRepo.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + postId));

        comment.setAuthor(user);
        post.addComment(comment);
        comment.setPost(post);
        // Save the updated post to the database
        postRepo.save(post);

        // Save the comment to the database
        commentRepo.save(comment);

        redirectAttributes.addFlashAttribute("message", "Comment uploaded successfully.");

        // Redirecting
        return "redirect:/newsfeed";
    }
    @RequestMapping("/post")
    public String post(@RequestParam(name = "postId", required = false) Long postId, Model model) {
        if (postId != null) {
            Optional<Post> optionalPost = postRepo.findById(postId);

            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                List<Comment> comments = commentRepo.findByPost_PostId(postId);
                model.addAttribute("comments", comments);
                model.addAttribute("post", post);
                model.addAttribute("comment", new Comment());
                return "post"; // Make sure you have a view named "post.html" or adjust accordingly
            }
        }

        // Handle the case where the postId is not provided or the post is not found
        return "redirect:/newsfeed"; // You can customize this URL as needed
    }
}
