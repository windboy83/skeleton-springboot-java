package com.sample.webflux.api.service;

import com.sample.domain.reactive.mongodb.product.ProductDocument;
import com.sample.domain.reactive.mysql.dto.command.TeamCommand;
import com.sample.domain.reactive.mysql.dto.info.TeamInfo;
import com.sample.webflux.api.common.tdd.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.CollectionUtils.isEmpty;

class SampleServiceTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(SampleService.class);

    @Autowired
    private SampleService sampleService;

    @Test
    void getAllTeams() {
        Flux<TeamInfo> teamFlux = sampleService.getAllTeams(new TeamCommand.SearchTeamCondition());
        List<TeamInfo> teamRes = teamFlux.collectList().block();

        assertThat(isEmpty(teamRes)).isFalse();
        assertThat(isEmpty(teamRes.get(0).getMembers())).isFalse();
    }

    @Test
    void getProduct() {

        Flux<ProductDocument> result = Flux.empty();
        for (int i = 0; i < 3; i++) {
            Pageable pageable = PageRequest.of(i, 10);
            result = Flux.merge(result, sampleService.getAllProduct(pageable));
        }

        StepVerifier
                .create(result)
                .expectNextCount(30)
                .expectComplete()
                .verify();
    }

    @Test
    void getProductWithPage() {

        Mono<Page<ProductDocument>> result = sampleService.getProductWithPage(PageRequest.of(0, 10));

        Page<ProductDocument> pageInfo = result.block();

        assertThat(pageInfo.getTotalElements()).isGreaterThanOrEqualTo(0);
        assertThat(pageInfo.get().count()).isEqualTo(10);
    }
}