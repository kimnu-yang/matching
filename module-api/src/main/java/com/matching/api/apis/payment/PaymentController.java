package com.matching.api.apis.payment;

import com.matching.api.advice.response.ApiResponse;
import com.matching.api.apis.payment.business.PaymentBusiness;
import com.matching.api.apis.payment.dto.PaymentConfirmRequest;
import com.matching.api.apis.payment.dto.PaymentResponse;
import com.matching.utils.advice.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Tag(name = "결제 관리")
public class PaymentController {

    private final PaymentBusiness paymentBusiness;

    @PostMapping(path = "/confirm")
    @Operation(summary = "토스 결제 검증", description = "토스 페이를 통해 결제된 내역을 검증하고 포인트를 추가합니다.")
    public ApiResponse<PaymentResponse> confirm(@Valid @RequestBody PaymentConfirmRequest request) {
        return ApiResponse.success(ResponseCode.READ_SUCCESS, paymentBusiness.confirmPayment(request));
    }
}
