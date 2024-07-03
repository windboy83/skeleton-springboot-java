package com.sample.domain.mysql.nio.domain;


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

@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Team {

    @Id
    @Column("seqNo")
    private Long seqNo;

    @Column("teamName")
    private String teamName;

    @CreatedDate
    @Column("createAt")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column("updateAt")
    private LocalDateTime updateAt;
}
