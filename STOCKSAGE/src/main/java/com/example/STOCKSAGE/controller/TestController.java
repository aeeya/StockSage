package com.example.STOCKSAGE.controller;

import com.example.STOCKSAGE.model.Product;
import com.example.STOCKSAGE.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class TestController {

    @Autowired
    private AIService aiService;

    @GetMapping("/dashboard-data")
    public Map<String, Object> getDashboardData() {
        // Terus panggil service untuk dapatkan data lengkap dashboard
        return aiService.getDecision();
    }

}


    
