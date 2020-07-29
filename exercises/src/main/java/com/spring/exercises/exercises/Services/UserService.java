package com.spring.exercises.exercises.Services;

import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers(int page, int limit);

    User getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByEmail(String email);
}
