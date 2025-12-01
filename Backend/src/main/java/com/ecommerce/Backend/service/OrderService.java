package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.Orders;

import java.util.List;

public interface OrderService {
    Orders placeOrder(Orders orders);
    List<Orders> getAllOrders();
    void cancelOrderById(Long id);
}
