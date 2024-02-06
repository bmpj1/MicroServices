package com.micro.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalProduct {
  private Long id;
  private String title;
  private String description;
  private BigDecimal price;
  // Otros campos como discountPercentage, rating, etc., según sean necesarios.
  private String brand;
  private String category;
  private String thumbnail;
  private List<String> images;
}