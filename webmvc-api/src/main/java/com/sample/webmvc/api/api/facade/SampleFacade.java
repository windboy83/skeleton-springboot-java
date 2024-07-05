package com.sample.webmvc.api.api.facade;

import com.sample.core.common.utils.StreamUtils;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import com.sample.webmvc.api.api.dto.response.MemberRes;
import com.sample.webmvc.api.api.dto.response.TeamRes;
import com.sample.webmvc.api.api.facade.mapper.SampleMapper;
import com.sample.webmvc.api.api.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
