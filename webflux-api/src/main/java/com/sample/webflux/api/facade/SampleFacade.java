package com.sample.webflux.api.facade;


import com.sample.webflux.api.dto.request.TeamReq;
import com.sample.webflux.api.dto.response.TeamRes;
import com.sample.webflux.api.facade.mapper.SampleMapper;
import com.sample.webflux.api.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import com.sample.domain.reactive.mysql.dto.command.TeamCommand;

@Component
@RequiredArgsConstructor
public class SampleFacade {
    private final SampleService sampleService;
    private final SampleMapper sampleMapper;

    public Flux<TeamRes> getAllTeams(TeamReq teamReq) {

        TeamCommand.SearchTeamCondition condition = sampleMapper.toTeamCommandSearchTeamCondition(teamReq);

        return sampleService.getAllTeams(condition)
                .map(sampleMapper::toSampleRes);
    }
}
