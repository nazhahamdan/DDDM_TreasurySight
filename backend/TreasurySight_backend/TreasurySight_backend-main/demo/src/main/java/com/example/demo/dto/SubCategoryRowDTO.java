package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class SubCategoryRowDTO {
    private String subCategory;
    private List<Double> values;
    private List<ItemDTO> items;
}
