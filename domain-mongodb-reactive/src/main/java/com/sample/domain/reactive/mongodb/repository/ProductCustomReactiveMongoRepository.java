package com.sample.domain.reactive.mongodb.repository;

import com.sample.domain.reactive.mongodb.product.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public interface ProductCustomReactiveMongoRepository {

    Flux<ProductDocument> findAllBy(Query query);

    Mono<Page<ProductDocument>> findAllBy(Query query, Pageable pageable);

    Mono<Long> countBy(Query query);
}

