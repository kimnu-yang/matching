package com.matching.api.apis.payment.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentConfirmRequest(
        @NotNull
        String paymentKey,
        @NotNull
        String orderId,
        @NotNull
        Long amount,
        @NotNull
        Long memberIdx
) {
}
