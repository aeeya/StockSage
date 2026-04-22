package com.example.STOCKSAGE.controller;

import com.example.STOCKSAGE.model.Product;
import com.example.STOCKSAGE.repository.ProductRepository;
import com.example.STOCKSAGE.service.AIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Penting: Supaya Live Server Aleeya boleh access backend Farhah
public class TestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AIService aiService;

    @GetMapping("/test-db")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/get-advice")
    public Map<String, Object> getAdvice() {
        // Tarik data dari Oracle DB Ain
        List<Product> products = productRepository.findAll();
        
        // Tukar list produk jadi String untuk hantar kat AI
        String inventoryString = products.toString(); 
        
        // Panggil service AI
        return aiService.getAdvice(inventoryString);
    }
}