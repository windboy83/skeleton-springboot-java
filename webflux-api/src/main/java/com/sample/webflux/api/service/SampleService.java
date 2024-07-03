package com.sample.webflux.api.service;

import com.sample.domain.mysql.nio.domain.Member;
import com.sample.domain.mysql.nio.domain.Team;
import com.sample.domain.mysql.nio.dto.command.TeamCommand;
import com.sample.domain.mysql.nio.dto.info.TeamInfo;
import com.sample.domain.mysql.nio.repository.TeamNioNioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.sample.domain.mysql.nio.repository.MemberNioNioRepository;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final MemberNioNioRepository memberNioRepository;
    private final TeamNioNioRepository teamNioRepository;

    public Team saveTeam(Team team) {
        return teamNioRepository.save(team).block();
    }

    public Member saveMember(Member member) {
        return memberNioRepository.save(member).block();
    }

    public Flux<TeamInfo> getAllTeams(TeamCommand.SearchTeamCondition condition) {
        return teamNioRepository.findAllTeamWithMemberBy(condition);
    }
}
