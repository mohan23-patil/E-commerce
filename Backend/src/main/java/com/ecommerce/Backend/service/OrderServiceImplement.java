package com.ecommerce.Backend.service;

import com.ecommerce.Backend.entity.Orders;
import com.ecommerce.Backend.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImplement implements OrderService{

   @Autowired
   private OrdersRepository ordersRepository;

    @Override
    public Orders placeOrder(Orders orders) {
        if (orders.getItems() != null) {
            orders.getItems().forEach(item -> {

            });
        }
        orders.setStatus("Pending");
        return ordersRepository.save(orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void cancelOrderById(Long id) {
        ordersRepository.deleteById(id);
    }
}
