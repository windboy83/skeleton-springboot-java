package com.sample.domain.mysql.repository;

import com.sample.domain.mysql.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {

    @Query("select distinct m from MemberEntity m join fetch m.team")
    List<MemberEntity> findAllFetchJoin();
}
