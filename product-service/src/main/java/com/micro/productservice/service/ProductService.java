package com.micro.productservice.service;

import com.micro.productservice.dto.ProductResponseDto;
import com.micro.productservice.model.ProductModel;

import java.util.List;

public interface ProductService {
  ProductModel createProduct(ProductModel productModel);
  void deleteProductById(Long id);
  List<ProductModel> getAllProducts(Integer perPage, Integer page);
  ProductModel getProductById(Long id);
  ProductModel updateProduct(Long id, ProductModel productModel);
  List<ProductModel> searchProducts(String query);
}
