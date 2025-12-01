package com.ecommerce.Backend.controller;

import com.ecommerce.Backend.entity.Cart;
import com.ecommerce.Backend.repository.CartRepository;
import com.ecommerce.Backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService service;

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
        return ResponseEntity.ok(service.addToCart(cart));
    }

    @GetMapping("/all")
    public List<Cart> getAllCart(){
        return service.getAllCart();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCartQuantity(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart existingCart = cartRepository.findById(id).orElseThrow();
        existingCart.setQuantity(updatedCart.getQuantity());
        cartRepository.save(existingCart);
        return ResponseEntity.ok(existingCart);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable Long id){
        service.deleteCartById(id);
        return ResponseEntity.ok("Cart Deleted SuccessFully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartRepository.deleteAll();
        return ResponseEntity.ok("Cart cleared");
    }
}
