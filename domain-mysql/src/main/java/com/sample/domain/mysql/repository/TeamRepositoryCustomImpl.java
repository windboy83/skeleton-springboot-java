package com.sample.domain.mysql.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.domain.mysql.domain.TeamEntity;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.sample.domain.mysql.domain.QMemberEntity.memberEntity;
import static com.sample.domain.mysql.domain.QTeamEntity.teamEntity;

@RequiredArgsConstructor
public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TeamEntity> getTeamListWithQueryDSL() {
        return queryFactory
                .select(teamEntity)
                .from(teamEntity)
                .innerJoin(memberEntity)
                .on(memberEntity.team.eq(teamEntity))
                .fetchJoin()
                .distinct()
                .fetch();
    }

    @Override
    public List<TeamInfo> findAllWithPaging(Pageable pageable) {
        List<Long> teamSeqNos = queryFactory.select(teamEntity.seqNo)
                .from(teamEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(teamEntity.seqNo.desc())
                .fetch();


        return queryFactory
                .from(teamEntity)
                .leftJoin(memberEntity).on(memberEntity.team.eq(teamEntity))
                .where(teamEntity.seqNo.in(teamSeqNos))
                .transform(
                        groupBy(teamEntity.seqNo).list(Projections.constructor(TeamInfo.class, teamEntity.seqNo, teamEntity.teamName,
                                list(Projections.constructor(MemberInfo.class, memberEntity.seqNo, memberEntity.name, teamEntity.teamName))))
                );
    }
}
