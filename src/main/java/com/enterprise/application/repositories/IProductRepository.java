package com.enterprise.application.repositories;

import com.enterprise.application.collections.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
