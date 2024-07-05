package com.sample.domain.reactive.mongodb.repository;

import com.sample.domain.reactive.mongodb.product.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductReactiveMongoRepository extends ReactiveMongoRepository<ProductDocument, String>, ProductCustomReactiveMongoRepository {
}

