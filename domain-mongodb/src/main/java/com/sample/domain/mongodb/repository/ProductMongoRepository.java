package com.sample.domain.mongodb.repository;

import com.sample.domain.mongodb.product.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String>, ProductCustomMongoRepository {
}

