package com.sample.webmvc.api.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRes {
    private Long seqNo;
    private String name;
    private String teamName;
}
