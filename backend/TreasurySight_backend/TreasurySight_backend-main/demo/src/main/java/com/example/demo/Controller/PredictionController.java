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

    @PostMapping("/faillite")
    public Map<String, Object> predireFaillite(
            @RequestBody Map<String, Object> donnees) {
        return predictionService.predireFaillite(donnees);
    }
}
