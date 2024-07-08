package com.sample.webmvc.api.api.service;

import com.sample.domain.mysql.domain.MemberEntity;
import com.sample.domain.mysql.domain.TeamEntity;
import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import com.sample.domain.mysql.repository.MemberRepository;
import com.sample.domain.mysql.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparingLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public List<TeamInfo> getTeamList() {
        List<TeamEntity> teamEntities = teamRepository.getTeamListWithQueryDSL();

        return teamEntities.stream()
                .map(TeamInfo::createBy)
                .sorted(comparingLong(TeamInfo::getSeqNo))
                .toList();
    }

    public List<TeamInfo> getTeamListWithPaging(Pageable pageable) {
        List<TeamInfo> memberInfos = teamRepository.findAllWithPaging(pageable);

        return memberInfos;
    }

    /**
     * N+1 발생 확인용
     *
     * @return
     */
    public List<MemberInfo> getMemberList() {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        List<MemberInfo> memberInfos = memberEntities.stream()
                .map(MemberInfo::createBy)
                .sorted(comparingLong(MemberInfo::getSeqNo))
                .toList();

        return memberInfos;
    }

    /**
     * native query fetch join
     *
     * @return
     */
    public List<MemberInfo> getMemberListWithFetchJoin() {
        List<MemberEntity> memberEntities = memberRepository.findAllFetchJoin();

        List<MemberInfo> memberInfos = memberEntities.stream()
                .map(MemberInfo::createBy)
                .sorted(comparingLong(MemberInfo::getSeqNo))
                .toList();

        return memberInfos;
    }

    /**
     * query DSL fetch join
     *
     * @return
     */
    public List<MemberInfo> getMemberListWithQueryDSL() {
        List<MemberEntity> memberEntities = memberRepository.findAllWithQueryDSL();

        List<MemberInfo> memberInfos = memberEntities.stream()
                .map(MemberInfo::createBy)
                .sorted(comparingLong(MemberInfo::getSeqNo))
                .toList();

        return memberInfos;
    }

    public TeamEntity saveTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity);
    }

    public MemberEntity saveMember(MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }
}
