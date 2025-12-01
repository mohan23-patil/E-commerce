package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.User;
import com.ecommerce.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User userRegistration(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> userLogin(String email, String password) {
        Optional<User> users = userRepository.findByEmail(email);
        if (users.isPresent() && users.get().getPassword().equals(password)){
            return users;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
