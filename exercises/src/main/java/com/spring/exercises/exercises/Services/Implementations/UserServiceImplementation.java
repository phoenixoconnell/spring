package com.spring.exercises.exercises.Services.Implementations;

import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.Services.UserService;
import com.spring.exercises.exercises.Shared.Utils;
import com.spring.exercises.exercises.dao.UserRepository;
import com.spring.exercises.exercises.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private Utils utils;

    public UserServiceImplementation(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public List<User> getAllUsers(int page, int limit) {
        List<User> returnValue;

        if (page>0) page --;
        PageRequest pageableRequest = PageRequest.of(page, limit);

        Page<User> userPage = userRepository.findAll(pageableRequest);

        returnValue = userPage.getContent();

        return returnValue;
    }

    @Override
    public User getUserById(Long id) {
        Optional <User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        user.setEncryptedPassword("password-test");
        user.setEmailVerification(true);
        user.setUserId(utils.generateUserId(30));

        User createdUser = userRepository.save(user);

        UserDTO returnUser = new UserDTO();
        BeanUtils.copyProperties(createdUser, returnUser);

        return returnUser;
    }

    @Override
    public void updateUser(User user) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        for (int i = 0; i<users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User returnUser = userRepository.findByEmail(email);
        return returnUser;
    }
}
