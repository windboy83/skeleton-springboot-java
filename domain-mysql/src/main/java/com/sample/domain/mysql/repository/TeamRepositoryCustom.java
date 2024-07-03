package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.TeamEntity;
import com.sample.domain.mysql.dto.info.TeamInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamRepositoryCustom {
    List<TeamEntity> getTeamListWithQueryDSL();

    List<TeamInfo> findAllWithPaging(Pageable pageable);
}
