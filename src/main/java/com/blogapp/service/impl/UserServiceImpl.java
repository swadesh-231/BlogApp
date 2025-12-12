package com.blogapp.service.impl;

import com.blogapp.entity.User;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        user.setUsername(user.getUsername().toLowerCase());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(ObjectId id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with username " + username));
    }

    @Override
    @Transactional
    public User updateUser(String username, User updatedUser) {
        User existing = findByUserName(username);

        existing.setPassword(updatedUser.getPassword());
        return userRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteUserById(ObjectId id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        User user = findByUserName(username);
        userRepository.deleteByUsername(user.getUsername());
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
