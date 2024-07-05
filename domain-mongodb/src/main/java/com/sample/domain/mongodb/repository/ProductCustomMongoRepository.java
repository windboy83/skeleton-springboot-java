package com.sample.domain.mongodb.repository;

import com.sample.domain.mongodb.product.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.stream.Stream;

public interface ProductCustomMongoRepository {

    Stream<ProductDocument> findAllBy(Query query);

    Page<ProductDocument> findAllBy(Query query, Pageable pageable);

    long countBy(Query query);
}

