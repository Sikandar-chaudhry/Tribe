package com.example.tribe_1.user;

public class AuthenticationService {

    private UserStorage userStorage;

    public AuthenticationService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User registerUser(String username, String email,String password,String first_name, String last_name) {
        // Check if the username is already taken
        if (userStorage.getUserByUsername(username) != null) {
            throw new RuntimeException("Username already taken");
        }

        // Create a new user
        User newUser = new User(username,email, password,first_name,last_name);

        // Store the user
        userStorage.addUser(newUser);

        return newUser;
    }

    public User loginUser(String username, String password) {
        // Check if the username exists
        User user = userStorage.getUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Check if the password is correct (in a real-world scenario, this would involve hashing and salting)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect password");
        }

        return user;
    }

}
