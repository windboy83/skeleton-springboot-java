package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {
}
