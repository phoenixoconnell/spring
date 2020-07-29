package com.spring.exercises.exercises.Controller;

import com.spring.exercises.exercises.Model.Request.UserRequest;
import com.spring.exercises.exercises.Model.Response.UserResponse;
import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.Services.UserService;
import com.spring.exercises.exercises.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam(value="page", defaultValue = "1") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<UserDTO> userDTOList = userService.getAllUsers(page, limit);
        List<UserResponse> userResponseList = new ArrayList<UserResponse>();

        for (int i = 0; i<userDTOList.size(); i++) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userDTOList.get(i), userResponse);
            userResponseList.add(userResponse);
        }

        return userResponseList;
    }

    @GetMapping(path="/{id}")
    public User getSingleUser(@PathVariable Long id) {
        User oneUser = userService.getUserById(id);
        return oneUser;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);

        UserDTO createdUser = userService.createUser(userDTO);

        UserResponse returnUser = new UserResponse();
        BeanUtils.copyProperties(createdUser, returnUser);

        return returnUser;
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
