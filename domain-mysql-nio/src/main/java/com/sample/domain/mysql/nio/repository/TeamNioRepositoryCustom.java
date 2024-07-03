package com.sample.domain.mysql.nio.repository;

import com.sample.domain.mysql.nio.domain.Team;
import com.sample.domain.mysql.nio.dto.command.TeamCommand;
import com.sample.domain.mysql.nio.dto.info.TeamInfo;
import reactor.core.publisher.Flux;

public interface TeamNioRepositoryCustom {
    Flux<Team> findAllTeamBy(TeamCommand.SearchTeamCondition condition);

    Flux<TeamInfo> findAllTeamWithMemberBy(TeamCommand.SearchTeamCondition condition);
}
