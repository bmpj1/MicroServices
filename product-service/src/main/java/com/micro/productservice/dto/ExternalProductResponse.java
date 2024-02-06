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
public class ExternalProductResponse {
  private List<ExternalProduct> products;
  private int total;
  private int skip;
  private int limit;
}
