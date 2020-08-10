package com.spring.exercises.exercises.dao;

import com.spring.exercises.exercises.Model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUserId(String userId);

    User findByEmail(String email);

    void deleteByUserId(String userId);
}
