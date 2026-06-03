package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.dto.DashboardDTO;
import com.example.demo.Service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{entrepriseId}")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable Long entrepriseId) {
        return ResponseEntity.ok(dashboardService.getDashboard(entrepriseId));
    }
}