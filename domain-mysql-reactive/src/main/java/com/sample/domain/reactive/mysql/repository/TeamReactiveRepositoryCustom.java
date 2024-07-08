package com.sample.domain.reactive.mysql.repository;

import com.sample.domain.reactive.mysql.domain.Team;
import com.sample.domain.reactive.mysql.dto.command.TeamCommand;
import com.sample.domain.reactive.mysql.dto.info.TeamInfo;
import reactor.core.publisher.Flux;

public interface TeamReactiveRepositoryCustom {
    Flux<Team> findAllTeamBy(TeamCommand.SearchTeamCondition condition);

    Flux<TeamInfo> findAllTeamWithMemberBy(TeamCommand.SearchTeamCondition condition);
}
