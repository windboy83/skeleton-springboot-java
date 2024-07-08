package com.sample.domain.mysql.dto.info;

import com.querydsl.core.annotations.QueryProjection;
import com.sample.domain.mysql.domain.MemberEntity;
import com.sample.domain.mysql.domain.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.Comparator.comparingLong;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamInfo {
    private Long seqNo;
    private String teamName;
    private List<MemberInfo> members;

    public static TeamInfo createBy(TeamEntity teamEntity) {
        return TeamInfo.builder()
                .seqNo(teamEntity.getSeqNo())
                .teamName(teamEntity.getTeamName())
                .members(teamEntity.getMembers().stream().map(MemberInfo::createBy)
                        .sorted(comparingLong(MemberInfo::getSeqNo)).toList())
                .build();
    }
}
