package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.PredictionService;
import java.util.Map;

@RestController
@RequestMapping("/api/prediction")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PredictionController {

    private final PredictionService predictionService;

    @GetMapping("/faillite")
    public Map<String, Object> predire() {
        return predictionService.predireFaillite();
    }
}