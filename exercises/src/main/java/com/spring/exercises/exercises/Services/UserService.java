package com.spring.exercises.exercises.Services;

import com.spring.exercises.exercises.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByEmail(String email);
}
