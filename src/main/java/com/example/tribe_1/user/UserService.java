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
    public void save(User user){
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any user with this ID : "+id);
    }


}
