/*package com.example.STOCKSAGE.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class AIService {

    @Value("${zai.api.key}")
    private String apiKey;

    @Value("${zai.api.url}")
    private String apiUrl;

    public String getDecision(String inventoryData) {
        RestTemplate restTemplate = new RestTemplate();

        // 1. Prepare Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // 2. Prepare the "Prompt" (The Instructions)
        String systemPrompt = "You are a Malaysian Retail Expert. I will give you inventory data. " +
                              "Give me a specific pricing strategy for each item to maximize profit. " +
                              "Keep it short and professional.";

        // 3. Create the Request Body (This structure is required by Z.AI)
        Map<String, Object> body = new HashMap<>();
        body.put("model", "glm-4"); // Or the specific model version given in hackathon
        body.put("messages", List.of(
            Map.of("role", "system", "content", systemPrompt),
            Map.of("role", "user", "content", "Here is my inventory: " + inventoryData)
        ));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // 4. Call the Brain
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Brain is sleepy: " + e.getMessage();
        }
    }
}

import org.springframework.stereotype.Service;

@Service
public class AIService {

    public String getAdvice(String inventoryData) {
        // This simulates a smart recommendation instantly
        return "{"
            + "\"status\": \"success\","
            + "\"recommendation\": ["
            + "  {\"item\": \"Gardenia Bread\", \"action\": \"Discount 30%\", \"reason\": \"Expires in 2 days\"},"
            + "  {\"item\": \"Dutch Lady Milk\", \"action\": \"Flash Sale\", \"reason\": \"High stock, low sales today\"},"
            + "  {\"item\": \"Nasi Lemak\", \"action\": \"Price +RM1\", \"reason\": \"Festival nearby tomorrow\"}"
            + "]"
            + "}";
    }
}*/

package com.example.STOCKSAGE.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AIService {

    public Map<String, Object> getDecision() {
        Map<String, Object> response = new HashMap<>();
        
        // 1. Data untuk Stats Cards kat atas Dashboard
        response.put("totalStockValue", "14,250.00");
        response.put("profitAtRisk", "1,120.00");
        response.put("estSavings", "845.00");

        // 2. Data Inventory Health (Table)
        List<Map<String, String>> inventory = new ArrayList<>();
        inventory.add(createItem("Susu Segar (1L)", "45 units", "25 Apr 2026", "Critical", "DAIRY-001"));
        inventory.add(createItem("Roti Gardenia Jumbo", "12 units", "28 Apr 2026", "Warning", "BAKE-102"));
        inventory.add(createItem("Coklat Cadbury", "120 units", "15 Jun 2026", "Safe", "CONF-554"));
        response.put("inventory", inventory);

        // 3. Z.AI Strategy Panel (Right Side)
        Map<String, Object> strategy = new HashMap<>();
        strategy.put("recommendation", "Launch a 'Happy Hour: Buy 1 Free 1' campaign for Susu Segar starting today at 4:00 PM.");
        
        List<String> logicInsights = Arrays.asList(
            "Inventory: High stock (45 units) vs Low shelf life (3 days).",
            "Environment: Rain forecast for tomorrow will drop foot traffic by 15%.",
            "Goal: Liquidate stock before expiry to save RM300.00 in cost."
        );
        strategy.put("logic", logicInsights);
        response.put("strategy", strategy);

        return response;
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