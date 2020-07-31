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

    @GetMapping(path="/{userID}")
    public UserResponse getUserByUserId(@PathVariable String userId) {
        UserDTO singleUserDTO = userService.getUserByUserId(userId);
        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(singleUserDTO, returnValue);
        return returnValue;
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

    @PutMapping(path="/{userId}")
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);
        userDTO.setUserId(userId);

        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(updatedUserDTO, returnValue);

        return returnValue;
    }

    @DeleteMapping(path="/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @GetMapping(path="/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        User oneUser = userService.getUserByEmail(email);
        return oneUser;
    }
}
