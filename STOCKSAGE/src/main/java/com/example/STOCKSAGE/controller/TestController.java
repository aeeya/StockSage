package com.example.STOCKSAGE.controller;

import com.example.STOCKSAGE.model.Product;
import com.example.STOCKSAGE.service.AIService;
import com.example.STOCKSAGE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class TestController {

    @Autowired
    private AIService aiService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/dashboard-data")
    public Map<String, Object> getDashboardData() {
        return aiService.getDecision();
    }

    @PostMapping("/update-stock")
    public Map<String, String> updateStock(@RequestBody Map<String, String> request) {
        try {
            Long id = Long.parseLong(request.get("productId"));
            Integer newStock = Integer.parseInt(request.get("stock"));
            String newExpiry = request.get("expiryDate");

            Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk ID " + id + " not found in database."));

            product.setStock(newStock);
            product.setExpiryDate(newExpiry);
            
            productRepository.save(product);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product updated successfully!");
            return response;
            
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return errorResponse;
        }
    }
}