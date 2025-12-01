package com.ecommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String name;
    @Column(length = 10000)
    private String description;
    @Column
    private double price;
    @Column
    private Integer stock;
    @Column
    private String category;

    private String image;

    private Double discount;

}
