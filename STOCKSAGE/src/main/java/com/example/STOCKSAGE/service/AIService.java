package com.example.STOCKSAGE.service;

/*import org.springframework.beans.factory.annotation.Value;
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
}*/

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
}
