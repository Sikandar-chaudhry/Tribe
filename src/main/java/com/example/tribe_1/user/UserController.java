package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        LinkedList<User> users = service.listAll();
        model.addAttribute("listUsers",users);
        return "userDisplay";
    }

}
