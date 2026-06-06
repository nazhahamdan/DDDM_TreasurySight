package com.example.demo.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.Service.AnalyticsClientService;
import com.example.demo.dto.ClientKpiDTO;
import com.example.demo.dto.ClientParetoDTO;
import com.example.demo.dto.ClientRiskDTO;
import com.example.demo.dto.ClientScatterDTO;
import com.example.demo.dto.DelayBucketDTO;

@RestController
@RequestMapping("/analytics/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientAnalyticsController {

    private final AnalyticsClientService service;

    public ClientAnalyticsController(
            AnalyticsClientService service
    ) {
        this.service = service;
    }

    @GetMapping("/{entrepriseId}")
    public List<ClientRiskDTO> getAnalysis(
            @PathVariable Long entrepriseId
    ) {
        return service.getClientAnalysis(entrepriseId);
    }
}