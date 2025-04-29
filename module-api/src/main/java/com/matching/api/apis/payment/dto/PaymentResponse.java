package com.matching.api.apis.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class PaymentResponse {
    Long idx;
    Long memberIdx;
    String orderId;
    String status;
    Long amount;
}
