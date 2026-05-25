package com.ecommerce.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminAuthController {

    private static  final String ADMIN_EMAIL = "adminQuickBuy@123";
    private static final String ADMIN_PASSWORD = "Admin@2307";

    @PostMapping("/login")
    
    public ResponseEntity<String> adminLogin(@RequestParam String email,@RequestParam String password){
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)){
            return ResponseEntity.ok("Login Successfully");
        }else {
            return ResponseEntity.status(401).body("Login Failed");
        }
    }
}
