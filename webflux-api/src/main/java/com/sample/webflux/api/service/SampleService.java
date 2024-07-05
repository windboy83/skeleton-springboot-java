package com.sample.webflux.api.service;

import com.sample.domain.reactive.mongodb.product.ProductDocument;
import com.sample.domain.reactive.mongodb.repository.ProductReactiveMongoRepository;
import com.sample.domain.reactive.mysql.domain.Member;
import com.sample.domain.reactive.mysql.domain.Team;
import com.sample.domain.reactive.mysql.dto.command.TeamCommand;
import com.sample.domain.reactive.mysql.dto.info.TeamInfo;
import com.sample.domain.reactive.mysql.repository.TeamReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.sample.domain.reactive.mysql.repository.MemberReactiveRepository;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final MemberReactiveRepository memberReactiveRepository;
    private final TeamReactiveRepository teamReactiveRepository;
    private final ProductReactiveMongoRepository productReactiveMongoRepository;

    public Team saveTeam(Team team) {
        return teamReactiveRepository.save(team).block();
    }

    public Member saveMember(Member member) {
        return memberReactiveRepository.save(member).block();
    }

    public Flux<TeamInfo> getAllTeams(TeamCommand.SearchTeamCondition condition) {
        return teamReactiveRepository.findAllTeamWithMemberBy(condition);
    }

    public Flux<ProductDocument> getAllProduct(Pageable pageable) {
        Query query = new Query(where("isUse").is(true))
                .with(pageable).skip((long) pageable.getPageSize() * pageable.getPageNumber()).limit(pageable.getPageSize());

        return productReactiveMongoRepository.findAllBy(query);
    }

    public Mono<Page<ProductDocument>> getProductWithPage(Pageable pageable) {
        Query query = new Query(where("isUse").is(true))
                .with(pageable).skip((long) pageable.getPageSize() * pageable.getPageNumber()).limit(pageable.getPageSize());

        return productReactiveMongoRepository.findAllBy(query, pageable);
    }
}
