package com.example.STOCKSAGE.controller;

import com.example.STOCKSAGE.model.Product;
import com.example.STOCKSAGE.repository.ProductRepository;
import com.example.STOCKSAGE.service.AIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
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
    public String getAdvice() {
        List<Product> products = productRepository.findAll();
        // Convert our products list to a simple string for the AI
        String inventoryString = products.toString(); 
        
        return aiService.getDecision(inventoryString);
    }
}

