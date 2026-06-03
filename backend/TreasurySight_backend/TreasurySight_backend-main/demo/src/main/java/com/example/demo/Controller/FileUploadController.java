package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.FileStorageService;
import com.example.demo.Service.MindeeService;
import com.example.demo.dto.RawTransaction;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;
    private final MindeeService mindeeService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            String path = fileStorageService.storeFile(file);   // local storage or cloud
            RawTransaction raw = mindeeService.parseFinancialDocument(file);
            return ResponseEntity.ok(raw);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}