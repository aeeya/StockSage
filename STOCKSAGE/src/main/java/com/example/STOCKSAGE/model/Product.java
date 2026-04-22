package com.example.STOCKSAGE.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    private String name;
    private String category;
    
    @Column(name = "BASE_PRICE")
    private Double basePrice;
}
