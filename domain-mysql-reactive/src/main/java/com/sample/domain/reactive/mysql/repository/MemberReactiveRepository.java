package com.sample.domain.reactive.mysql.repository;

import com.sample.domain.reactive.mysql.domain.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MemberReactiveRepository extends ReactiveCrudRepository<Member, Long>, MemberReactiveRepositoryCustom {

    Flux<Member> findAllByTeamSeqNo(Long teamSeqNo);
}
