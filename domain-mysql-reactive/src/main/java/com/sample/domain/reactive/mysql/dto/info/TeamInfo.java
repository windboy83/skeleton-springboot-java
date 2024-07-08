package com.sample.domain.reactive.mysql.dto.info;

import com.sample.domain.reactive.mysql.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeamInfo {
    private Long seqNo;
    private String teamName;
    private List<Member> members;
}
