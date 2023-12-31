package com.example.tribe_1;

import com.example.tribe_1.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/signin")
    public String showLandingPage(HttpSession session) {
        session.invalidate();
        return "signin";
    }
    @GetMapping("")
    public String noURL(){
        return "redirect:/signin";
    }

    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

}

