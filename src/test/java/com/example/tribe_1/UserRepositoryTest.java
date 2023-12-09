package com.example.tribe_1;

import com.example.tribe_1.data_structures.LinkedList;
import com.example.tribe_1.user.AuthenticationService;
import com.example.tribe_1.user.User;
import com.example.tribe_1.user.UserRepository;
import com.example.tribe_1.user.UserStorage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setUsername("sikander");
        user.setEmail("sikandarmukhtar@gmail.com");
        user.setFirst_name("Sikander");
        user.setLast_name("Mukhtar");
        user.setPassword("Sikander12345");

        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
            UserStorage userStorage = new UserStorage();
        for (User user: users){
            userStorage.addUser(user);
        }

        LinkedList<User> users1  = userStorage.getUsers();
        for (User user: users1){
            System.out.println(user);
        }
    }

}
