package com.sample.domain.mysql.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class QueryDSLConfig {

    @PersistenceContext(unitName = "master")
    private EntityManager masterEntityManager;

    @Primary
    @Bean(name = "queryFactory")
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(masterEntityManager);
    }

    @PersistenceContext(unitName = "read")
    private EntityManager readEntityManager;

    @Bean(name = "readQueryFactory")
    public JPAQueryFactory readQueryFactory() {
        return new JPAQueryFactory(readEntityManager);
    }
}
