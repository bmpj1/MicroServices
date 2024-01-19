package com.micro.productservice.controller;

import com.micro.productservice.dto.ProductResponseDto;
import com.micro.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
  @Autowired
    private final ProductService productService;
  @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}
