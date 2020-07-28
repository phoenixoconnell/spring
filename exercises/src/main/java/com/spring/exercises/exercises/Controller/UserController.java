package com.spring.exercises.exercises.Controller;

import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping(path="/{id}")
    public User getSingleUser(@PathVariable Long id) {
        User oneUser = userService.getOneUser(id);
        return oneUser;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
