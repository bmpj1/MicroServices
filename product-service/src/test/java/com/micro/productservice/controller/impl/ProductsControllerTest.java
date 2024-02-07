package com.micro.productservice.controller.impl;

import com.micro.productservice.dto.ExternalProduct;
import com.micro.productservice.dto.ExternalProductResponse;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
@AutoConfigureMockMvc
class ProductsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  //region SearchProducts
  @Test
  void searchProductsShouldReturnMatchingProducts() throws Exception {
    // GIVEN The ProductFacade mock list of products.
    List<ProductModel> products = List.of(
            new ProductModel(1L, "Product 1", "Description 1", BigDecimal.valueOf(100), "Brand 1"),
            new ProductModel(2L, "Product 2", "Description 2", BigDecimal.valueOf(200), "Brand 2")
    );

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(productService.searchProducts(anyString())).thenReturn(products);

    // THEN Do the actual call to the method under test.
    // AND VERIFY The results of the method under test.
    mockMvc.perform(get("/v1/products/search")
            .param("query", "Product")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Product 1"))
            .andExpect(jsonPath("$[0].description").value("Description 1"))
            .andExpect(jsonPath("$[0].price").value(100))
            .andExpect(jsonPath("$[0].brand").value("Brand 1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Product 2"))
            .andExpect(jsonPath("$[1].description").value("Description 2"))
            .andExpect(jsonPath("$[1].price").value(200))
            .andExpect(jsonPath("$[1].brand").value("Brand 2"));

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(productService).searchProducts("Product");
  }


  @Test
  void searchProductsShouldReturnEmptyListIfNoMatches() throws Exception {
    // GIVEN The ProductFacade mock list of products.
    List<ProductModel> products = List.of();

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(productService.searchProducts(anyString())).thenReturn(products);

    // THEN Do the actual call to the method under test.
    // AND VERIFY The results of the method under test.
    mockMvc.perform(get("/v1/products/search")
            .param("query", "Product")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[]"));

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(productService).searchProducts("Product");
  }

  //endregion SearchProducts
}
