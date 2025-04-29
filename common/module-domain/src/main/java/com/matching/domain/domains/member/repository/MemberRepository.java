package com.matching.domain.domains.member.repository;

import com.matching.storage.database.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 전체 조회
    Page<Member> findAll(Pageable pageable);
}