package com.project.java_challenge.services;

import com.project.java_challenge.dtos.UserRegisterDTO;
import com.project.java_challenge.models.Role;
import com.project.java_challenge.models.User;
import com.project.java_challenge.repositories.RoleRepository;
import com.project.java_challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User registerUser(UserRegisterDTO userRegisterDTO) {
        if (existsByUsername(userRegisterDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setAdmin(userRegisterDTO.isAdmin());

        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);
        
        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if (optionalRoleAdmin.isPresent()) {
                roles.add(optionalRoleAdmin.get());
            } else {
                throw new IllegalArgumentException("ROLE_ADMIN does not exist in the database.");
            }
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}