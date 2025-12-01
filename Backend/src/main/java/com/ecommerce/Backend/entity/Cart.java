package com.ecommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private String image;
    private Double discount;
}
