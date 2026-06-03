package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class CategoryRowDTO {
    private String category;
    private List<Double> values;
    private List<SubCategoryRowDTO> subCategories;
}
