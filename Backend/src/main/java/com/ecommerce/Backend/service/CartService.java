package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart addToCart(Cart cart);
    List<Cart> getAllCart();
    void deleteCartById(Long id);
}
