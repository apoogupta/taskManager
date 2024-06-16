package com.project.taskmanager.service;

import com.project.taskmanager.entity.User;
import com.project.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean userExists(String username){
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public void createUser(String username, String password){
        User user = new User(username,password);
        userRepository.save(user);
    }
}