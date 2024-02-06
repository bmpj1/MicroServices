package com.micro.productservice.service.impl;

import com.micro.productservice.dto.ProductResponseDto;
import com.micro.productservice.facade.ProductFacade;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductFacade productFacade;

  @Autowired
  public ProductServiceImpl(ProductFacade productFacade) {
    this.productFacade = productFacade;
  }
  /**
   * @param productModel
   * @return
   */
  @Override
  public ProductModel createProduct(ProductModel productModel) {
    return null;
  }

  /**
   * @param id
   */
  @Override
  public void deleteProductById(Long id) {

  }

  /**
   * @param perPage
   * @param page
   * @return
   */
  @Override
  public List<ProductModel> getAllProducts(Integer perPage, Integer page) {
    return productFacade.getAllProducts();
  }

  /**
   * @param id
   * @return
   */
  @Override
  public ProductModel getProductById(Long id) {
    return productFacade.getProductById(id);
  }

  /**
   * @param id
   * @param productModel
   * @return
   */
  @Override
  public ProductModel updateProduct(Long id, ProductModel productModel) {
    return null;
  }

  /**
   * @param query
   * @return
   */
  @Override
  public List<ProductModel> searchProducts(String query) {
    return productFacade.searchProducts(query);
  }
}
