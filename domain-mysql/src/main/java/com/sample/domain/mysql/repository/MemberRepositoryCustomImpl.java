package com.sample.domain.mysql.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.domain.mysql.domain.MemberEntity;
import com.sample.domain.mysql.domain.TeamEntity;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;
import static com.sample.domain.mysql.domain.QMemberEntity.memberEntity;
import static com.sample.domain.mysql.domain.QTeamEntity.teamEntity;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberEntity> findAllWithQueryDSL() {
        return queryFactory
                .select(memberEntity)
                .from(memberEntity)
                .join(memberEntity.team).fetchJoin()
                .distinct()
                .fetch();
    }
}
