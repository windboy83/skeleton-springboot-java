package com.sample.domain.mysql.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
