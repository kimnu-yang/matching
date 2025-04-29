package com.matching.api.apis.payment.business;

import com.matching.api.apis.payment.dto.PaymentConfirmRequest;
import com.matching.api.apis.payment.dto.PaymentResponse;
import com.matching.domain.domains.member.service.MemberService;
import com.matching.domain.domains.payment.service.PaymentService;
import com.matching.domain.domains.point.service.PointService;
import com.matching.storage.database.model.payment.Payment;
import com.matching.storage.database.model.point.Point;
import com.matching.utils.advice.exception.CustomException;
import com.matching.utils.advice.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentBusiness {

    private final ModelMapper mapper;
    private final RestTemplate restTemplate;

    private final MemberService memberService;
    private final PaymentService paymentService;
    private final PointService pointService;

    private static final String TOSS_SECRET = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm"; // 테스트용 시크릿 키
    private static final String TOSS_API_URL = "https://api.tosspayments.com/v1/payments/confirm";

    // 결제 정보 검증 후 포인트 추가
    @Transactional
    public PaymentResponse confirmPayment(PaymentConfirmRequest request) {

        if(memberService.findByIdx(request.memberIdx()) == null) throw new CustomException(ResponseCode.MEMBER_NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String encodedKey = Base64.getEncoder().encodeToString((TOSS_SECRET + ":").getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedKey);

        Map<String, Object> body = Map.of(
        "paymentKey", request.paymentKey(),
        "orderId", request.orderId(),
        "amount", request.amount()
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(TOSS_API_URL, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) throw new CustomException(ResponseCode.PAYMENT_CONFIRM_FAIL);
        } catch (HttpClientErrorException e) {
            throw new CustomException(ResponseCode.PAYMENT_CONFIRM_FAIL);
        }

        Payment payment = Payment.builder()
                .memberIdx(request.memberIdx())
                .orderId(request.orderId())
                .amount(request.amount())
                .status("CONFIRM")
                .build();
        payment = paymentService.save(payment);

        Point point = Point.builder()
                .paymentIdx(payment.getIdx())
                .memberIdx(request.memberIdx())
                .amount(request.amount())
                .build();
        pointService.save(point);

        return mapper.map(payment, PaymentResponse.class);
    }
}
