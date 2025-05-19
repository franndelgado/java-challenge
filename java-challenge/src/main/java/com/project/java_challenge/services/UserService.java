package com.project.java_challenge.services;

import com.project.java_challenge.dtos.UserRegisterDTO;
import com.project.java_challenge.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
    User registerUser(UserRegisterDTO userRegisterDTO);
}
