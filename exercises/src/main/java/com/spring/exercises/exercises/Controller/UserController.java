package com.spring.exercises.exercises.Controller;

import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userServices;

    public UserController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userServices.getAllUsers();
        return users;
    }

    @GetMapping(path="/{id}")
    public User getSingleUser() {
        return null;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {

    }
}
