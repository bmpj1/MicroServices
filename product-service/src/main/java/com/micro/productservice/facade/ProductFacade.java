package com.micro.productservice.facade;

import com.micro.productservice.dto.ExternalProduct;
import com.micro.productservice.dto.ExternalProductResponse;
import com.micro.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductFacade {

  private final RestTemplate restTemplate;

  // Inyecta RestTemplate a través del constructor
  @Autowired
  public ProductFacade(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<ProductModel> getAllProducts() {
    String url = "https://dummyjson.com/products";
    ExternalProductResponse response = restTemplate.getForObject(url, ExternalProductResponse.class);

    if (response != null) {
      return response.getProducts().stream().map(this::convertToProductModel).toList();
      // Aquí puedes llamar a productService para guardar o procesar estos productos según sea necesario.
    }
    return List.of(); // Retorna una lista vacía si no hay respuesta
  }

  // Suponiendo que tienes un método o constructor para convertir ExternalProduct a ProductModel
  public ProductModel getProductById(Long id) {
    String url = "https://dummyjson.com/products/{id}";

    // Asume que ExternalProduct es la clase correcta para un único producto
    ExternalProduct externalProduct = restTemplate.getForObject(url, ExternalProduct.class, id);

    if (externalProduct != null) {
      return convertToProductModel(externalProduct);
    }
    return null; // Retorna null o podrías lanzar una excepción personalizada si el producto no se encuentra
  }

  public List<ProductModel> searchProducts(String query) {
    String baseUrl = "https://dummyjson.com/products/search?q={query}";

    // Realiza la llamada a la API y obtiene la respuesta
    ExternalProductResponse response = restTemplate.getForObject(baseUrl, ExternalProductResponse.class, query);

    // Verifica si la respuesta contiene productos y conviértelos a tu modelo de dominio
    if (response != null && response.getProducts() != null) {
      return response.getProducts().stream()
              .map(this::convertToProductModel).toList();
    }

    return List.of(); // Retorna una lista vacía si no se encuentran productos
  }

  private ProductModel convertToProductModel(ExternalProduct externalProduct) {
    // Transforma ExternalProduct a ProductModel. Ajusta los nombres de los campos y tipos según sea necesario.
    return ProductModel.builder()
            .id(externalProduct.getId())
            .name(externalProduct.getTitle())
            .description(externalProduct.getDescription())
            .price(externalProduct.getPrice())
            // .brand(externalProduct.getBrand()) // Asume que ProductModel tiene un campo brand
            .build();
  }

}
