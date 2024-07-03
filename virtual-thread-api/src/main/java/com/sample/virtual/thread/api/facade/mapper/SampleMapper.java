package com.sample.virtual.thread.api.facade.mapper;

import com.sample.domain.mysql.dto.command.TeamCommand;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import com.sample.virtual.thread.api.dto.request.TeamReq;
import com.sample.virtual.thread.api.dto.response.MemberRes;
import com.sample.virtual.thread.api.dto.response.TeamRes;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SampleMapper {
    TeamRes toTeamRes(TeamInfo teamInfo);

    TeamCommand.SearchTeamCondition toTeamCommandSearchTeamCondition(TeamReq teamReq);

    MemberRes toMemberRes(MemberInfo memberInfo);
}
