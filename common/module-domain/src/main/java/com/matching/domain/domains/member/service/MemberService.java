package com.matching.domain.domains.member.service;

import com.matching.domain.domains.member.repository.MemberRepository;
import com.matching.storage.database.model.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByIdx(Long idx) {
        return memberRepository.findById(idx).orElse(null);
    }

    public List<Member> findAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return memberRepository.findAll(pageable).getContent();
    }

    public Member save(Member member) { return memberRepository.save(member); }
}
