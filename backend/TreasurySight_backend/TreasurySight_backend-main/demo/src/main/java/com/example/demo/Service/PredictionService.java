package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final RestTemplate restTemplate;
    private final String FLASK_URL = "http://localhost:5000/predict";

    public Map<String, Object> predireFaillite() {
        try {
            return restTemplate.getForObject(
                    FLASK_URL,
                    Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Erreur Flask : " + e.getMessage());
        }
    }
}