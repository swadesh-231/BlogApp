package com.blogapp.service;

import com.blogapp.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(ObjectId id);
    User findByUserName(String username);
    User updateUser(String username, User updatedUser);
    void deleteUserById(ObjectId id);
    void deleteUserByUsername(String username);
    User saveUser(User user);
}

