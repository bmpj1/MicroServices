package com.micro.productservice.service.impl;

import com.micro.productservice.model.Product;
import com.micro.productservice.model.ProductModel;
import com.micro.productservice.repository.ProductRepository;
import com.micro.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel createProduct(ProductModel productModel) {
        Product product = convertToProduct(productModel);
        Product savedProduct = productRepository.save(product);
        return convertToProductModel(savedProduct);
    }

    private Product convertToProduct(ProductModel productModel) {
        return Product.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice())
                .build();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductModel> getAllProducts(Integer perPage, Integer page) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToProductModel)
                .toList();
    }

    @Override
    public ProductModel getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToProductModel)
                .orElse(null);
    }

    @Override
    public ProductModel updateProduct(Long id, ProductModel productModel) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productModel.getName());
                    existingProduct.setDescription(productModel.getDescription());
                    existingProduct.setPrice(productModel.getPrice());
                    Product updatedProduct = productRepository.save(existingProduct);
                    return convertToProductModel(updatedProduct);
                })
                .orElse(null);
    }

    @Override
    public List<ProductModel> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContaining(query);
        return products.stream()
                .map(this::convertToProductModel)
                .toList();
    }

    private ProductModel convertToProductModel(Product product) {
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
