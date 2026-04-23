package com.example.STOCKSAGE.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    private String name;
    private String category;
    
    @Column(name = "BASE_PRICE")
    private Double basePrice;

    private Integer stock;
    
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;
}