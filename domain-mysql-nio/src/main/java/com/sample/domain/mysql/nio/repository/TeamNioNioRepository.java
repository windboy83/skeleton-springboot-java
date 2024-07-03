package com.sample.domain.mysql.nio.repository;

import com.sample.domain.mysql.nio.domain.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeamNioNioRepository extends ReactiveCrudRepository<Team, Long>, TeamNioRepositoryCustom {
}
