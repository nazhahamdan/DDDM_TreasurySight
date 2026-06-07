package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final RestTemplate restTemplate;
    @Value("${flask.api.url}")
    private String FLASK_URL;

    public Map<String, Object> predireFaillite() {
        try {
            return restTemplate.getForObject(
                    FLASK_URL + "/predict",
                    Map.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Erreur Flask : " + e.getMessage());
        }
    }
}