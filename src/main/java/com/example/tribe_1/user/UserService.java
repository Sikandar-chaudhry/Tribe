package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired private UserRepository repo;
    public LinkedList<User> listAll(){
        Iterable<User> users = repo.findAll();
        UserStorage userStorage = new UserStorage();
        for (User user: users){
                userStorage.addUser(user);
        }
        LinkedList<User> userList = userStorage.getUsers();
        return userList;
    }

    public boolean user_is_authenticated(String email, String password){
        Iterable<User> users = repo.findAll();
        boolean found = false;
        UserStorage userStorage = new UserStorage();
        for (User user: users){
            userStorage.addUser(user);
        }
        LinkedList<User> userList = userStorage.getUsers();
        for (User user: userList){
            if (user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    found = true;
                    user.setAuthenticated(true);
                }
            }
        }
        return found;
    };
    public User getUserByEmailAndPassword(String email, String password){
        Iterable<User> users = repo.findAll();
        User foundUser = null;
        UserStorage userStorage = new UserStorage();
        for (User user: users){
            userStorage.addUser(user);
        }
        LinkedList<User> userList = userStorage.getUsers();
        for (User user: users){
            if (user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    foundUser = user;
                    user.setAuthenticated(true);
                }
            }
        }
        return foundUser;
    }


    public void save(User user){
        repo.save(user);
        Iterable<User> users = repo.findAll();
        boolean found = false;
        UserStorage userStorage = new UserStorage();
        for (User eachUser: users){
            userStorage.addUser(eachUser);
        }
        LinkedList<User> userList = userStorage.getUsers();
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any user with this ID : "+id);
    }


}
