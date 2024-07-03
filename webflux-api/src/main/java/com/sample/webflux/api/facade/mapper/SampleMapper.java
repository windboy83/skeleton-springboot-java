package com.sample.webflux.api.facade.mapper;

import com.sample.domain.mysql.nio.dto.command.TeamCommand;
import com.sample.domain.mysql.nio.dto.info.TeamInfo;
import com.sample.webflux.api.dto.request.TeamReq;
import com.sample.webflux.api.dto.response.TeamRes;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SampleMapper {
    TeamRes toSampleRes(TeamInfo teamInfo);

    TeamCommand.SearchTeamCondition toTeamCommandSearchTeamCondition(TeamReq teamReq);
}
