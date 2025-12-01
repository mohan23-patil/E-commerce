package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User userRegistration(User user);
    List<User> getAllUsers();
    Optional<User> userLogin(String email,String password);
    Optional<User> getUserByEmail(String email);
}
