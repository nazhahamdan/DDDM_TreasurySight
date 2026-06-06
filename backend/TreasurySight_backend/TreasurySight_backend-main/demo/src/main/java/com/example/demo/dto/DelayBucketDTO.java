package com.example.demo.dto;

import lombok.Data;

@Data
public class DelayBucketDTO {
    private String range; // "0-5", "5-15", "15+"
    private Long count;
}