package com.ecommerce.Backend.controller;

import com.ecommerce.Backend.entity.Orders;
import com.ecommerce.Backend.repository.OrdersRepository;
import com.ecommerce.Backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @PostMapping("/place")
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders orders) {
        return ResponseEntity.ok(service.placeOrder(orders));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrder() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrderById(@PathVariable Long id){
        service.cancelOrderById(id);
        return ResponseEntity.ok("Order Has been Cancel");
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return ResponseEntity.ok("Status updated to " + status);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable String email) {
        List<Orders> userOrders = ordersRepository.findByEmail(email);
        return ResponseEntity.ok(userOrders);
    }

}
