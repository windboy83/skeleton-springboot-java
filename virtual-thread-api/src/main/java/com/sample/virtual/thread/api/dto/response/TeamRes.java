package com.sample.virtual.thread.api.dto.response;

import com.sample.domain.mysql.dto.info.MemberInfo;
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
    private List<MemberInfo> members;
}
