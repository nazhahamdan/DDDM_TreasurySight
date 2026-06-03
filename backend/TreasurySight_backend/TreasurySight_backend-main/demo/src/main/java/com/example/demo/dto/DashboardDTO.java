package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardDTO {
    private List<String> months;
    private List<Double> cashIn;
    private List<Double> cashOut;
    private List<Double> balance;
    private List<Boolean> isForecast;

    // for dashboard table
    private List<CategoryRowDTO> table;
}