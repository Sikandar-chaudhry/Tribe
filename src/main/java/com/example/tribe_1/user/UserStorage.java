package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.LinkedList;

public class UserStorage {
    public UserStorage() {
        users = new LinkedList<>();
    }

    private static LinkedList<User> users = new LinkedList<>();
    public void addUser(User user) {
        users.insertAtEnd(user);
    }

    public static LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public User getUserByUsername(String username) {
        for(User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
            return null; // User not found
    }

    public User getUserById(long userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null; // User not found
    }

}
