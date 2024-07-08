package com.sample.webflux.api.dto.response;

import com.sample.domain.reactive.mysql.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeamRes {
    private Long seqNo;
    private String teamName;
    private List<Member> members;
}
