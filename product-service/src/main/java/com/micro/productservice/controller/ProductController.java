package com.micro.productservice.controller;

import com.micro.openapi.api.ApiApi;
import com.micro.openapi.model.ProductResponseDto;
import com.micro.productservice.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ApiApi {

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        // TODO make the service call
        return productService.getAllProducts();
    }

}
