package com.spring.exercises.exercises.Services;

import com.spring.exercises.exercises.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers(int page, int limit);

    UserDTO getUserByUserId(String id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(String userId);

    UserDTO getUserByEmail(String email);
}
