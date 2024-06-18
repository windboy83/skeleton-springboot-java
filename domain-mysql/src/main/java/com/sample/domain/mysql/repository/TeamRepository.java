package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>, TeamRepositoryCustom {
}
