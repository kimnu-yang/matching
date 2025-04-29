package com.matching.api.apis.member;

import com.matching.api.advice.response.ApiResponse;
import com.matching.api.apis.member.business.MemberBusiness;
import com.matching.api.apis.member.dto.MemberResponse;
import com.matching.utils.advice.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Tag(name = "회원 관리")
public class MemberController {

    private final MemberBusiness memberBusiness;

    @GetMapping(path = "/list")
    @Operation(summary = "회원 리스트 조회", description = "필터링에 부합하는 회원 리스트를 조회합니다.")
    public ApiResponse<List<MemberResponse>> getMemberList(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "desc") String direction
    ) {
        return ApiResponse.success(ResponseCode.READ_SUCCESS, memberBusiness.getMemberList(page, size, sortBy, direction));
    }

    @GetMapping(path = "/{memberIdx}")
    @Operation(summary = "회원 조회", description = "회원 정보를 조회합니다.(프로필 조회수 추가)")
    public ApiResponse<MemberResponse> getMember(@PathVariable(value = "memberIdx") Long memberIdx) {
        return ApiResponse.success(ResponseCode.READ_SUCCESS, memberBusiness.getMember(memberIdx));
    }
}
