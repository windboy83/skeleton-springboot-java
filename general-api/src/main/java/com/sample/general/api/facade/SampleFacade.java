package com.sample.general.api.facade;

import com.sample.core.common.utils.StreamUtils;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import com.sample.general.api.dto.request.TeamReq;
import com.sample.general.api.dto.response.MemberRes;
import com.sample.general.api.dto.response.TeamRes;
import com.sample.general.api.facade.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.sample.general.api.service.SampleService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleFacade {
    private final SampleService sampleService;
    private final SampleMapper sampleMapper;

    public List<TeamRes> getTeamList() {
        List<TeamInfo> teams = sampleService.getTeamList();

        return teams.stream().map(c -> sampleMapper.toTeamRes(c)).toList();
    }

    public List<MemberRes> getMembers() {
        List<MemberInfo> members = sampleService.getMemberListWithQueryDSL();

        return StreamUtils.map(members, c -> sampleMapper.toMemberRes(c));
    }
}
