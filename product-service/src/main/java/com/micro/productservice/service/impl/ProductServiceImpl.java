package com.micro.productservice.service.impl;

import com.micro.productservice.dto.ProductResponseDto;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

  private final Map<Long, ProductModel> productRepository = new HashMap<>();
  private long nextId = 1;

  @Override
  public ProductModel createProduct(ProductModel productModel) {
    productModel.setId(nextId);
    productRepository.put(nextId, productModel);
    nextId++;
    return productModel;
  }

  @Override
  public void deleteProductById(Long id) {
    productRepository.remove(id);
  }

  @Override
  public List<ProductModel> getAllProducts(Integer perPage, Integer page) {
    return new ArrayList<>(productRepository.values());
  }

  @Override
  public ProductModel getProductById(Long id) {
    return productRepository.get(id);
  }

  @Override
  public ProductModel updateProduct(Long id, ProductModel productModel) {
    productModel.setId(id);
    productRepository.put(id, productModel);
    return productModel;
  }

  @Override
    public ProductResponseDto getProduct(Long id) {
      return ProductResponseDto.builder()
        .id(String.valueOf(id))
        .name("Product " + id)
        .description("Description of product " + id)
        .price(1000.0)
        .name("Nombre " + id)
        .description("Descripción del producto " + id)
        .build();
    }
}
