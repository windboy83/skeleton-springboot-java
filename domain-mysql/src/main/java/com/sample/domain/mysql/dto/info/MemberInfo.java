package com.sample.domain.mysql.dto.info;

import com.sample.domain.mysql.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {
    private Long seqNo;
    private String name;
    private String teamName;

    public static MemberInfo createBy(MemberEntity memberEntity) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setName(memberEntity.getName());
        memberInfo.setSeqNo(memberEntity.getSeqNo());
        memberInfo.setTeamName(memberEntity.getTeam().getTeamName());

        return memberInfo;
    }
}
