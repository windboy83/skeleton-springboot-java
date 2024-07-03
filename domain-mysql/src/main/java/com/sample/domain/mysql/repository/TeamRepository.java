package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>, TeamRepositoryCustom {

    @Query("select t from TeamEntity t join t.members")
    List<TeamEntity> findAllFetchJoin();
}
