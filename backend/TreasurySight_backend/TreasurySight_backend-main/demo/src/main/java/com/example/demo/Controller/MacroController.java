package com.example.demo.Controller;

import com.example.demo.Service.MacroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/macro")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MacroController {

    private final MacroService macroService;

    @GetMapping("/indicateurs")
    public List<Map<String, Object>> getIndicateurs() {
        return macroService.getAllIndicateurs();
    }
}
