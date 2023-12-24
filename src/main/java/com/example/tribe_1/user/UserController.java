package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.LinkedList;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired private UserService service;
    @Autowired private PostRepository postRepo;

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

}
