package com.sample.domain.mysql.nio.repository;

import com.sample.domain.mysql.nio.domain.Member;
import com.sample.domain.mysql.nio.domain.Team;
import com.sample.domain.mysql.nio.dto.command.TeamCommand;
import com.sample.domain.mysql.nio.dto.info.TeamInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;

import static java.util.Comparator.comparingLong;

@RequiredArgsConstructor
public class TeamNioRepositoryCustomImpl implements TeamNioRepositoryCustom {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;


    @Override
    public Flux<Team> findAllTeamBy(TeamCommand.SearchTeamCondition condition) {
        return r2dbcEntityTemplate
                .select(Team.class)
                .all();
    }

    @Override
    public Flux<TeamInfo> findAllTeamWithMemberBy(TeamCommand.SearchTeamCondition condition) {
        /*return r2dbcEntityTemplate
                .select(Team.class)
                .all()
                .sort((o1, o2) -> Math.toIntExact(o1.getSeqNo() - o2.getSeqNo()))
                .flatMap(team -> r2dbcEntityTemplate.select(Member.class)
                        .matching(query(where("teamSeqNo").is(team.getSeqNo())))
                        .all()
                        .collectSortedList((o1, o2) -> Math.toIntExact(o1.getSeqNo() - o2.getSeqNo()))
                        .flatMap(members -> Mono.just(TeamInfo.builder()
                                        .seqNo(team.getSeqNo())
                                        .teamName(team.getTeamName())
                                        .members(members)
                                .build())));*/

        final String query = "select t.seqNo, t.teamName, m.seqNo as memberSeqNo, m.name from team t inner join member m on (t.seqNo = m.team_seqNo)";

        return r2dbcEntityTemplate.getDatabaseClient()
                .sql(query)
                .fetch()
                .all()
                .bufferUntilChanged(r -> r.get("seqNo"))
                .map(rows -> TeamInfo.builder()
                        .seqNo((Long) rows.get(0).get("seqNo"))
                        .teamName((String) rows.get(0).get("teamName"))
                        .members(rows.stream()
                                .map(r -> Member.builder()
                                        .seqNo((Long) r.get("memberSeqNo"))
                                        .name((String) r.get("name"))
                                        .teamSeqNo((Long) r.get("seqNo"))
                                        .build())
                                .sorted(comparingLong(Member::getSeqNo))
                                .toList()
                        )
                        .build())
                .sort(comparingLong(TeamInfo::getSeqNo));
    }
}
