package com.sample.domain.reactive.mysql.repository;

import com.sample.domain.reactive.mysql.domain.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeamReactiveRepository extends ReactiveCrudRepository<Team, Long>, TeamReactiveRepositoryCustom {
}
