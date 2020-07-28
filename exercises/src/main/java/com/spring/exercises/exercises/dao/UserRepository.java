package com.spring.exercises.exercises.dao;

import com.spring.exercises.exercises.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
