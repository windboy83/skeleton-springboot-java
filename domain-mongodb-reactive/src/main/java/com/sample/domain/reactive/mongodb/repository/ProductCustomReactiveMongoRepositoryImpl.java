package com.sample.domain.reactive.mongodb.repository;


import com.sample.domain.reactive.mongodb.product.ProductDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProductCustomReactiveMongoRepositoryImpl implements ProductCustomReactiveMongoRepository {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<ProductDocument> findAllBy(Query query) {
        return reactiveMongoTemplate.find(query, ProductDocument.class);
    }

    @Override
    public Mono<Page<ProductDocument>> findAllBy(Query query, Pageable pageable) {
        Mono<List<ProductDocument>> productDocuments = reactiveMongoTemplate.find(query, ProductDocument.class).collectList();
        Mono<Long> count = reactiveMongoTemplate.count(query, ProductDocument.class);
        return productDocuments.zipWith(count)
                .map(tuple -> PageableExecutionUtils. getPage(tuple.getT1(), pageable, tuple::getT2));
    }

    @Override
    public Mono<Long> countBy(Query query) {
        return reactiveMongoTemplate.count(query, ProductDocument.class);
    }
}

