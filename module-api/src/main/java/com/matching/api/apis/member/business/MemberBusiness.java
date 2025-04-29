package com.matching.api.apis.member.business;

import com.matching.api.apis.member.dto.MemberResponse;
import com.matching.domain.domains.member.service.MemberService;
import com.matching.storage.database.model.member.Member;
import com.matching.utils.advice.exception.CustomException;
import com.matching.utils.advice.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberBusiness {

    private final ModelMapper mapper;
    private final MemberService memberService;

    // 필터링에 맞는 모든 멤버 데이터 조회
    @Transactional(readOnly = true)
    public List<MemberResponse> getMemberList(int page, int size, String sortBy, String direction) {

        List<MemberResponse> responseList = new ArrayList<>(List.of());
        List<Member> memberList = memberService.findAll(page, size, sortBy, direction);
        memberList.forEach(member -> {
            MemberResponse response = mapper.map(member, MemberResponse.class);
            responseList.add(response);
        });

        return responseList;
    }

    // 단일 회원 조회(프로필 조회 수 상승)
    @Transactional
    public MemberResponse getMember(Long memberIdx) {

        Member member = memberService.findByIdx(memberIdx);
        if(member == null) throw new CustomException(ResponseCode.MEMBER_NOT_FOUND);

        member = memberService.save(member.toBuilder().hits(member.getHits()+1).build());

        return mapper.map(member, MemberResponse.class);
    }
}
