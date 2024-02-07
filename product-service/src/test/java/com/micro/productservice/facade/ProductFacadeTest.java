package com.micro.productservice.facade;

import com.micro.productservice.dto.ExternalProduct;
import com.micro.productservice.dto.ExternalProductResponse;
import com.micro.productservice.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductFacadeTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ProductFacade productFacade;

  @BeforeEach
  void setUp() {
    // Aquí puedes configurar tus mocks o cualquier configuración inicial común necesaria.
  }

  /**
   * Method to create a mock product.
   * @param id
   * @param title
   * @param description
   * @param price
   * @return
   */
  private ExternalProduct createMockProduct(Long id, String title, String description, BigDecimal price) {
    ExternalProduct product = new ExternalProduct();
    product.setId(id);
    product.setTitle(title);
    product.setDescription(description);
    product.setPrice(price);
    return product;
  }

  // region GetAllProducts
  @Test
  void getAllProductsShouldReturnProducts() {
    // GIVEN The mock response for all products.
    ExternalProduct product1 = createMockProduct(3L, "Phone", "A nice phone", BigDecimal.valueOf(300));
    ExternalProduct product2 = createMockProduct(4L, "Another Phone", "Another nice phone", BigDecimal.valueOf(400));
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of(product1, product2));

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class))).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.getAllProducts();

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertEquals(2, products.size()),
            () -> assertTrue(products.stream().anyMatch(p -> p.getName().contains("Phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getDescription().contains("nice phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getName().contains("Another Phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getDescription().contains("Another nice phone"))),
            () -> assertTrue(products.stream().allMatch(p -> p.getPrice().compareTo(BigDecimal.ZERO) > 0))  // Verifica que todos los precios sean mayores que cero
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class));
  }

  @Test
  void getAllProductsShouldReturnEmptyListIfNoMatches() {
    // GIVEN The mock response for a search query with no matches.
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of());

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class))).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.getAllProducts();

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertTrue(products.isEmpty())
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class));
  }
  @Test
  void getAllProductsShouldReturnEmptyListIfNoResponse() {
    // GIVEN The mock response for a search query with no matches.
    ExternalProductResponse mockResponse = null;

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class), anyString())).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.searchProducts("phone");

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertTrue(products.isEmpty())
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class), anyString());
  }
  // endregion GetAllProducts

  //region GetProductById
  @Test
  void getProductByIdShouldReturnProduct() {
    // GIVEN The mock response for a specific product.
    ExternalProduct mockProduct = createMockProduct(2L, "Specific Product", "Specific Description", BigDecimal.valueOf(200));
    ExternalProduct product1 = createMockProduct(3L, "Phone", "A nice phone", BigDecimal.valueOf(300));
    ExternalProduct product2 = createMockProduct(4L, "Another Phone", "Another nice phone", BigDecimal.valueOf(400));
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of(product1, product2));

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProduct.class), eq(2L))).thenReturn(mockProduct);

    // THEN Do the actual call to the method under test.
    ProductModel product = productFacade.getProductById(2L);

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(product),
            () -> assertEquals("Specific Product", product.getName()),
            () -> assertEquals("Specific Description", product.getDescription()),
            () -> assertEquals(BigDecimal.valueOf(200), product.getPrice())
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProduct.class), eq(2L));
  }

  @Test
  void getProductByIdShouldReturnNullIfNoMatches() {
    // GIVEN The mock response for a specific product.
    ExternalProduct product1 = createMockProduct(3L, "Phone", "A nice phone", BigDecimal.valueOf(300));
    ExternalProduct product2 = createMockProduct(4L, "Another Phone", "Another nice phone", BigDecimal.valueOf(400));
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of(product1, product2));

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProduct.class), eq(2L))).thenReturn(null);

    // THEN Do the actual call to the method under test.
    ProductModel product = productFacade.getProductById(2L);

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNull(product)
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProduct.class), eq(2L));
  }

  @Test
  void getProductByIdShouldReturnNullIfNoResponse() {
    // GIVEN The mock response for a specific product.
    ExternalProductResponse mockResponse = null;

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class), anyString())).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.searchProducts("phone");

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertTrue(products.isEmpty())
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class), anyString());
  }
  //endregion GetProductById

  //region SearchProducts
  @Test
  void searchProductsShouldReturnMatchingProducts() {
    // GIVEN The mock response for a search query.
    ExternalProduct product1 = createMockProduct(3L, "Phone", "A nice phone", BigDecimal.valueOf(300));
    ExternalProduct product2 = createMockProduct(4L, "Another Phone", "Another nice phone", BigDecimal.valueOf(400));
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of(product1, product2));

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class), anyString())).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.searchProducts("phone");

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertEquals(2, products.size()),
            () -> assertTrue(products.stream().anyMatch(p -> p.getName().contains("Phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getDescription().contains("nice phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getName().contains("Another Phone"))),
            () -> assertTrue(products.stream().anyMatch(p -> p.getDescription().contains("Another nice phone"))),
            () -> assertTrue(products.stream().allMatch(p -> p.getPrice().compareTo(BigDecimal.ZERO) > 0))  // Verifica que todos los precios sean mayores que cero
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class), anyString());
  }

  @Test
  void searchProductsShouldReturnEmptyListIfNoMatches() {
    // GIVEN The mock response for a search query with no matches.
    ExternalProductResponse mockResponse = new ExternalProductResponse();
    mockResponse.setProducts(List.of());

    // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
    when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class), anyString())).thenReturn(mockResponse);

    // THEN Do the actual call to the method under test.
    List<ProductModel> products = productFacade.searchProducts("phone");

    // METHOD CALL VERIFY The results of the method under test.
    assertAll(
            () -> assertNotNull(products),
            () -> assertTrue(products.isEmpty())
    );

    // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
    verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class), anyString());
  }

  @Test
  void searchProductsShouldReturnEmptyListIfNoResponse() {
      // GIVEN The mock response for a search query with no matches.
      ExternalProductResponse mockResponse = null;

      // WHEN The mocked method is called inside the method under test, return the mocked data instead of calling the actual method.
      when(restTemplate.getForObject(anyString(), eq(ExternalProductResponse.class), anyString())).thenReturn(mockResponse);

      // THEN Do the actual call to the method under test.
      List<ProductModel> products = productFacade.searchProducts("phone");

      // METHOD CALL VERIFY The results of the method under test.
      assertAll(
              () -> assertNotNull(products),
              () -> assertTrue(products.isEmpty())
      );

      // MOCKED METHOD CALL VERIFY The mocked method was called once with the correct parameters.
      verify(restTemplate).getForObject(anyString(), eq(ExternalProductResponse.class), anyString());
  }
  //endregion SearchProducts
}
