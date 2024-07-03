package com.sample.webflux.api.service;

import com.sample.domain.mysql.nio.dto.command.TeamCommand;
import com.sample.domain.mysql.nio.dto.info.TeamInfo;
import com.sample.webflux.api.common.tdd.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.CollectionUtils.isEmpty;

class SampleServiceTest extends BaseTest {
    @Autowired
    private SampleService sampleService;

    @Test
    void getAllTeams() {
        Flux<TeamInfo> teamFlux = sampleService.getAllTeams(new TeamCommand.SearchTeamCondition());
        List<TeamInfo> teamRes = teamFlux.collectList().block();

        assertFalse(isEmpty(teamRes));
        assertFalse(isEmpty(teamRes.get(0).getMembers()));
    }
}