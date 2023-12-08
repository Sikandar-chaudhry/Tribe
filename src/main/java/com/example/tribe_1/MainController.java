package com.example.tribe_1;

import com.example.tribe_1.data_structures.LinkedList;
import com.example.tribe_1.user.User;
import com.example.tribe_1.user.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/users")
    public String displayUsers(Model model){
        User user1 = new User(1,"s", "sikander", "1234");
        User user2 = new User(2,"si", "ali", "5678");
        User user3 = new User(3,"sik", "asim", "9101");
        User user4 = new User(4,"sika", "hamza", "1213");
        UserStorage userStorage = new UserStorage();
        userStorage.addUser(user1);
        userStorage.addUser(user2);
        userStorage.addUser(user3);
        userStorage.addUser(user4);
        LinkedList<User> users = UserStorage.getUsers();
        model.addAttribute("listUsers", users);
        return "userDisplay";
    }


}
