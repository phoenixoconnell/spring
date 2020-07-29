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
        User oneUser = userService.getUserById(id);
        return oneUser;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping(path="/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path="/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        User oneUser = userService.getUserByEmail(email);
        return oneUser;
    }
}
