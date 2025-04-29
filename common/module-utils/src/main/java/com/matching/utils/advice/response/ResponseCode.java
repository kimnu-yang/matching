package com.matching.utils.advice.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseCode {
    // Common (C0000)
    // 200 OK
    READ_SUCCESS(HttpStatus.OK, "C2001", "성공적으로 조회되었습니다."),
    UPDATE_SUCCESS(HttpStatus.OK, "C2002", "성공적으로 수정되었습니다."),
    DELETE_SUCCESS(HttpStatus.OK, "C2003", "성공적으로 삭제되었습니다."),
    // 201 Created
    CREATE_SUCCESS(HttpStatus.CREATED, "C2011", "성공적으로 등록되었습니다."),
    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C4001", "잘못된 요청입니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "C4002", "파라미터 데이터가 전달되지 않았습니다."),
    // 401 Unauthorized
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "C4011", "접근 권한이 없습니다."),
    AUTHENTICATION_ENTRYPOINT(HttpStatus.UNAUTHORIZED, "C4012", "리소스 접근 권한이 없습니다."),
    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, "C4031", "권한이 없습니다."),
    // 404 Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND, "C4041", "요청한 리소스가 없습니다."),
    NULL_POINT(HttpStatus.NOT_FOUND, "C4042", "NULL 값이 발생했습니다."),
    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C4051", "허용되지 않은 메소드입니다."),
    // 422 Unprocessable Entity
    NOT_VALID_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "C4221", "파라미터 값이 유효하지 않습니다."),
    REQUEST_MISSING(HttpStatus.UNPROCESSABLE_ENTITY, "C4222", "필수 파라미터 값이 누락되었습니다."),
    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C5001", "서버에 오류가 발생하였습니다."),

    // Member (M0000)
    // 404 Not Found
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M4041", "존재하지 않는 회원번호입니다."),

    // PAYMENT (PA0000)
    // 400 Bad Request
    PAYMENT_CONFIRM_FAIL(HttpStatus.BAD_REQUEST, "PA400", "결제 검증에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
