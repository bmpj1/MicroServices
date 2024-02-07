package com.micro.productservice.service.impl;

import com.micro.productservice.facade.ProductFacade;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductFacade productFacade;

  @InjectMocks
  private ProductServiceImpl productService;

  @BeforeEach
  void setUp() {
    // Aquí puedes configurar tus mocks o cualquier configuración inicial necesaria.
  }

  @Test
  void getAllProductsShouldReturnProducts() {
    // Simular el facade para devolver una lista de productos
    when(productFacade.getAllProducts()).thenReturn(List.of(
            new ProductModel(1L, "Product 1", "Description 1", BigDecimal.valueOf(100), "Brand 1"),
            new ProductModel(2L, "Product 2", "Description 2", BigDecimal.valueOf(200), "Brand 2")
    ));

    List<ProductModel> products = productService.getAllProducts(10, 0);

    assertAll(
            () -> assertNotNull(products, "La lista de productos no debe ser nula"),
            () -> assertEquals(2, products.size(), "La lista de productos debe tener 2 elementos")
    );
  }

  @Test
  void getProductByIdShouldReturnProduct() {
    // Simular el facade para devolver un producto específico
    when(productFacade.getProductById(1L)).thenReturn(
            new ProductModel(1L, "Product 1", "Description 1", BigDecimal.valueOf(100), "Brand 1")
    );

    ProductModel product = productService.getProductById(1L);

    assertAll(
            () -> assertNotNull(product, "El producto no debe ser nulo"),
            () -> assertEquals("Product 1", product.getName(), "El nombre del producto debe coincidir")
    );
  }

  @Test
  void searchProductsShouldReturnMatchingProducts() {
    // Simular el facade para devolver productos coincidentes
    when(productFacade.searchProducts("phone")).thenReturn(List.of(
            new ProductModel(3L, "Phone 1", "Description 3", BigDecimal.valueOf(300), "Brand 3")
    ));

    List<ProductModel> products = productService.searchProducts("phone");

    assertAll(
            () -> assertNotNull(products, "La lista de productos no debe ser nula"),
            () -> assertEquals(1, products.size(), "La lista de productos debe tener 1 elemento"),
            () -> assertTrue(products.get(0).getName().contains("Phone"), "El nombre del producto debe contener 'Phone'")
    );
  }

  @Test
  void getAllProductsShouldReturnEmptyListWhenNoData() {
    // Simular el facade para devolver una lista vacía
    when(productFacade.getAllProducts()).thenReturn(List.of());

    List<ProductModel> products = productService.getAllProducts(10, 0);

    assertTrue(products.isEmpty(), "La lista de productos debe estar vacía");
  }

  @Test
  void getProductByIdShouldReturnNullWhenNoData() {
    // Simular el facade para devolver null
    when(productFacade.getProductById(anyLong())).thenReturn(null);

    ProductModel product = productService.getProductById(1L);

    assertNull(product, "El producto debe ser nulo cuando no hay datos");
  }

  @Test
  void searchProductsShouldReturnEmptyListWhenNoMatches() {
    // Simular el facade para devolver una lista vacía en búsqueda
    when(productFacade.searchProducts("nonexistent")).thenReturn(List.of());

    List<ProductModel> products = productService.searchProducts("nonexistent");

    assertTrue(products.isEmpty(), "La lista de productos debe estar vacía cuando no hay coincidencias");
  }
}
