package com.micro.productservice.controller.impl;

import com.micro.productservice.controller.ProductsApi;
import com.micro.productservice.model.InlineResponse200Model;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${openapi.product.base-path:/v1}")
public class ProductsController implements ProductsApi {

  // Suponiendo que tienes un servicio para manejar operaciones de productos
  private final ProductService productService;

  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public ResponseEntity<ProductModel> createProduct(ProductModel productModel) {
    // Implementar la lógica para crear un producto
    ProductModel createdProduct = productService.createProduct(productModel);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<ProductModel> deleteProductById(Long id) {
    // Implementar la lógica para eliminar un producto por su ID
    productService.deleteProductById(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<List<ProductModel>> getAllProducts(Integer perPage, Integer page) {
    // Implementar la lógica para recuperar todos los productos
    List<ProductModel> products = productService.getAllProducts(perPage, page);
    return ResponseEntity.ok(products);
  }

  @Override
  public ResponseEntity<ProductModel> getProductById(Long id) {
    // Implementar la lógica para recuperar un producto por su ID
    ProductModel product = productService.getProductById(id);
    if (product != null) {
      return ResponseEntity.ok(product);
    }
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<InlineResponse200Model> updateProduct(Long id, ProductModel productModel) {
    // Implementar la lógica para actualizar un producto
    ProductModel updatedProduct = productService.updateProduct(id, productModel);
    InlineResponse200Model response = new InlineResponse200Model();
    response.setProduct(updatedProduct);
    return ResponseEntity.ok(response);
  }
}
