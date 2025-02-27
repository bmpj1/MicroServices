package com.micro.productservice.repository;

import com.micro.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Long> {

    List<Product> findByNameContaining(String name);

}
