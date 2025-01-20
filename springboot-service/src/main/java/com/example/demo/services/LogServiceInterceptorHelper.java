package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogServiceInterceptorHelper {
    private final String LOGS_SERVICE_URL = "http://logs-service:5001/api/logs";

    public void addLog(String message) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> request = new HashMap<>();
        request.put("message", message);
        restTemplate.postForObject(LOGS_SERVICE_URL, request, Void.class);
    }
}