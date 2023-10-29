package com.micro.productservice.domain.repository;

import com.micro.productservice.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {

}
