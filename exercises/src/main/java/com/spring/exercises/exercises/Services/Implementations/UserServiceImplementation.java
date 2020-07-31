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

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private Utils utils;

    public UserServiceImplementation(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public List<UserDTO> getAllUsers(int page, int limit) {
        List<User> returnValue = new ArrayList<User>();

        if (page>0) page --;
        PageRequest pageableRequest = PageRequest.of(page, limit);

        Page<User> userPageList = userRepository.findAll(pageableRequest);
        returnValue = userPageList.getContent();

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        for (int i = 0; i<returnValue.size(); i++) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(returnValue.get(i), userDTO);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
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
    public UserDTO updateUser(UserDTO userDTO) {
        User foundUser = userRepository.findByUserId(userDTO.getUserId());
        BeanUtils.copyProperties(userDTO, foundUser);
        User updatedUser = userRepository.save(foundUser);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        User returnUser = userRepository.findByEmail(email);
        return returnUser;
    }
}
