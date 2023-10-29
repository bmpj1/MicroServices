package com.micro.productservice.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

  @Id
  private Long id;

  @NotEmpty(message = "Product name is required")
  private String name;

  @Size(max = 250, message = "Product description must be at most 250 characters")
  private String description;

  @Min(value = 1, message = "Product price must be greater than 1")
  private BigDecimal price;

  private String brand;

}
