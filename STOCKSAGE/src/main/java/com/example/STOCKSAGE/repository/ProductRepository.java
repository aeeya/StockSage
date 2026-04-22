package com.example.STOCKSAGE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.STOCKSAGE.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
