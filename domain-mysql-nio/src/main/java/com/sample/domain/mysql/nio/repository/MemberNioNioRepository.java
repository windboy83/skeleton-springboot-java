package com.sample.domain.mysql.nio.repository;

import com.sample.domain.mysql.nio.domain.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MemberNioNioRepository extends ReactiveCrudRepository<Member, Long>, MemberNioRepositoryCustom {

    Flux<Member> findAllByTeamSeqNo(Long teamSeqNo);
}
