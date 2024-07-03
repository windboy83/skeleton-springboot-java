package com.sample.virtual.thread.api.service;

import com.sample.domain.mysql.dto.info.MemberInfo;
import com.sample.domain.mysql.dto.info.TeamInfo;
import com.sample.virtual.thread.api.common.tdd.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.util.CollectionUtils.isEmpty;


@SpringBootTest
class SampleServiceTest extends BaseTest {
    @Autowired
    private SampleService sampleService;

    @BeforeEach
    void beforeEach() {
        /*TeamEntity team1 = TeamEntity.builder().teamName("1팀").build();
        sampleService.saveTeam(team1);
        TeamEntity team2 = TeamEntity.builder().teamName("2팀").build();
        sampleService.saveTeam(team2);
        TeamEntity team3 = TeamEntity.builder().teamName("3팀").build();
        sampleService.saveTeam(team3);
        TeamEntity team4 = TeamEntity.builder().teamName("4팀").build();
        sampleService.saveTeam(team4);
        TeamEntity team5 = TeamEntity.builder().teamName("5팀").build();
        sampleService.saveTeam(team5);

        List<TeamEntity> teams = new ArrayList<>();
        for (int i=1;i<=5;i++) {
            TeamEntity team = TeamEntity.builder().teamName(i + "팀").build();
            sampleService.saveTeam(team);
            teams.add(team);
        }
        for (TeamEntity team : teams) {
            List<MemberEntity> members = new ArrayList<>();
            for (int i=1;i<=5;i++) {
                MemberEntity memberEntity = MemberEntity.builder().name(team.getTeamName() + "_" + i + "멤버").team(team).build();
                sampleService.saveMember(memberEntity);
                members.add(memberEntity);
            }
            team.setMembers(members);
            sampleService.saveTeam(team);
        }*/
    }

    @Test
    void getTeamList() {
        List<TeamInfo> teamEntityList = sampleService.getTeamList();
        assertFalse(isEmpty(teamEntityList));
    }

    @Test
    void getTeamListWithPaging() {
        Pageable pageable = PageRequest.of(1, 2);
        List<TeamInfo> teamEntityList = sampleService.getTeamListWithPaging(pageable);
        assertFalse(isEmpty(teamEntityList));
    }

    @Test
    void getMemberList() {
        List<MemberInfo> memberList = sampleService.getMemberList();
        assertFalse(isEmpty(memberList));

        List<MemberInfo> memberList2 = sampleService.getMemberListWithFetchJoin();
        assertFalse(isEmpty(memberList2));

        List<MemberInfo> memberList3 = sampleService.getMemberListWithQueryDSL();
        assertFalse(isEmpty(memberList3));

        for (int i = 0; i < memberList.size(); i++) {
            MemberInfo memberInfo1 = memberList.get(i);
            MemberInfo memberInfo2 = memberList2.get(i);
            MemberInfo memberInfo3 = memberList2.get(i);

            assertThat(memberInfo1.getTeamName())
                    .isEqualTo(memberInfo2.getTeamName())
                    .isEqualTo(memberInfo3.getTeamName());
        }
    }
}