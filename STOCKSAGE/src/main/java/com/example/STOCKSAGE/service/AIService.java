package com.example.STOCKSAGE.service;

import com.example.STOCKSAGE.model.Product;
import com.example.STOCKSAGE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class AIService {

    @Autowired
    private ProductRepository productRepository; 

    public Map<String, Object> getDecision() {
    Map<String, Object> response = new HashMap<>();
    List<Product> realProducts = productRepository.findAll();

    double totalStockValue = 0;
    double profitAtRisk = 0;

    List<Map<String, String>> inventoryList = new ArrayList<>();
    
    for (Product p : realProducts) {
        int stock = (p.getStock() != null) ? p.getStock() : 0;
        double price = (p.getBasePrice() != null) ? p.getBasePrice() : 0.0;
        String status = calculateStatus(stock, p.getExpiryDate());

        // Calculate Global Total Value
        totalStockValue += (stock * price);

        // Calculate Profit at Risk: Sum value of items that are Critical or Warning
        if (status.equals("Critical")) {
            profitAtRisk += (stock * price);
        } else if (status.equals("Warning")) {
            profitAtRisk += (stock * price * 0.5); // 50% risk for warning items
        }

        inventoryList.add(createItem(
            p.getName(), 
            stock + " units", 
            p.getExpiryDate(), 
            status, 
            "SKU-" + p.getProductId()
        ));
    }

    // Est. Savings: Z.AI assumes we can recover 75% of the risk through dynamic pricing
    double estSavings = profitAtRisk * 0.75;

    response.put("totalStockValue", String.format("%.2f", totalStockValue));
    response.put("profitAtRisk", String.format("%.2f", profitAtRisk));
    response.put("estSavings", String.format("%.2f", estSavings));
    response.put("inventory", inventoryList);

    // Dynamic Strategy Recommendation
    Map<String, Object> strategy = new HashMap<>();
    if (profitAtRisk > 0) {
        strategy.put("recommendation", "Z.AI Alert: High loss risk detected! Execute 'Quick-Clearance' for items expiring soon.");
        strategy.put("logic", Arrays.asList(
            "Detected RM " + String.format("%.2f", profitAtRisk) + " in high-risk inventory.",
            "Dynamic pricing strategy can recover up to 75% of potential losses.",
            "Priority: Items with 'Critical' status."
        ));
    } else {
        strategy.put("recommendation", "Inventory healthy. Maintain current procurement cycle.");
        strategy.put("logic", Arrays.asList("No immediate expiry or low-stock risks detected."));
    }
    
    response.put("strategy", strategy);

    return response;
    }

    private String calculateStatus(Integer stock, String expiryDate) {
    // 1. Check for Null values
    if (stock == null) return "N/A";

    // 2. Quantity Logic (Stock Levels)
    if (stock <= 5) return "Critical"; // Very low stock
    if (stock <= 20) return "Warning"; // Running low

    // 3. Expiry Date Logic
    if (expiryDate != null && !expiryDate.isEmpty()) {
        try {
            // HTML date input sends format YYYY-MM-DD
            LocalDate expiry = LocalDate.parse(expiryDate);
            LocalDate today = LocalDate.now();

            if (expiry.isBefore(today) || expiry.isEqual(today)) {
                return "Critical"; // Already expired or expires today
            }
            if (expiry.isBefore(today.plusDays(7))) {
                return "Warning";  // Expires within 7 days
            }
        } catch (DateTimeParseException e) {
            // Fallback if date format is not YYYY-MM-DD
            return "Format Error";
        }
    }
    return "Safe"; // Default if stock is high and date is far away
  }

    private Map<String, String> createItem(String name, String stock, String expiry, String status, String sku) {
        Map<String, String> item = new HashMap<>();
        item.put("name", name);
        item.put("stock", stock);
        item.put("expiry", expiry);
        item.put("status", status);
        item.put("sku", sku);
        return item;
    }
}