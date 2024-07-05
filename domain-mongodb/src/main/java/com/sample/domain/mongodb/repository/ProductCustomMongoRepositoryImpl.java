package com.sample.domain.mongodb.repository;


import com.sample.domain.mongodb.product.ProductDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class ProductCustomMongoRepositoryImpl implements ProductCustomMongoRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Stream<ProductDocument> findAllBy(Query query) {
        return mongoTemplate.stream(query, ProductDocument.class);
    }

    @Override
    public Page<ProductDocument> findAllBy(Query query, Pageable pageable) {
        List<ProductDocument> productDocuments = mongoTemplate.find(query, ProductDocument.class);
        return PageableExecutionUtils.getPage(
                productDocuments,
                pageable,
                () -> mongoTemplate.count(query.skip(-1).limit(-1), ProductDocument.class)
        );
    }

    @Override
    public long countBy(Query query) {
        return mongoTemplate.count(query, ProductDocument.class);
    }
}

