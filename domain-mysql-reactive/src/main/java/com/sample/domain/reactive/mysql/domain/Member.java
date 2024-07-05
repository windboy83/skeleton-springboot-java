package com.sample.domain.reactive.mysql.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Member {

    @Id
    @Column("seqNo")
    private Long seqNo;

    @Column("name")
    private String name;

    @Column("team_seqNo")
    private Long teamSeqNo;

    @CreatedDate
    @Column("createAt")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column("updateAt")
    private LocalDateTime updateAt;
}
