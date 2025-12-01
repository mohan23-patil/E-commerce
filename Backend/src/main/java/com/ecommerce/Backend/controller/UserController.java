package com.ecommerce.Backend.controller;

import com.ecommerce.Backend.entity.User;
import com.ecommerce.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> userRegister(@RequestBody User user){
        return ResponseEntity.ok(service.userRegistration(user));
    }

    @PostMapping("/login")
    public Optional<User> userLogin(@RequestBody User user){
        return service.userLogin(user.getEmail(),user.getPassword());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = service.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String email, @RequestBody User updatedUser) {
        Optional<User> existingUser = service.getUserByEmail(email);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setPassword(updatedUser.getPassword());


            User savedUser = service.userRegistration(user); // or a dedicated update method
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }


}
