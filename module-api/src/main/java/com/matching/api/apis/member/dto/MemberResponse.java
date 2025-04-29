package com.matching.api.apis.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MemberResponse{
    Long idx;
    String name;
    Long hits;
    LocalDateTime createdAt;
}
