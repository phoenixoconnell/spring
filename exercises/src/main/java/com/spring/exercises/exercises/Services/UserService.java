package com.spring.exercises.exercises.Services;

import com.spring.exercises.exercises.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getOneUser(Long id);

    void createUser(User user);
}
