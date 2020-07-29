package com.spring.exercises.exercises.Services.Implementations;

import com.spring.exercises.exercises.Model.User;
import com.spring.exercises.exercises.Services.UserService;
import com.spring.exercises.exercises.dao.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void createUser(User user) {
        userRepository.save(user);
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
