package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.MemberEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberEntity> findAllWithQueryDSL();
}
