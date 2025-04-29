package com.matching.api.advice;

import com.matching.api.advice.response.ApiResponse;
import com.matching.utils.advice.exception.CustomException;
import com.matching.utils.advice.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    // Custom Exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException e) {
        ResponseCode code = e.getCode();
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // 내부 Exception
    // 필수 파라미터 값이 누락되었을 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResponseCode code = ResponseCode.REQUEST_MISSING;
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // 파라미터 데이터값이 유효하지 않을 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(BindingResult bindingResult) {
        ResponseCode code = ResponseCode.NOT_VALID_ENTITY;
        print(new Object(){}, code.getMessage(), bindingResult);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.failure(code, bindingResult));
    }

    // Request 파라미터 값이 넘어오지 않을 경우
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Object>> handleMissingRequestHeaderExceptionException(MissingServletRequestParameterException e) {
        ResponseCode code = ResponseCode.MISSING_REQUEST_PARAMETER;
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // 잘못된 서버 요청일 경우
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(HttpClientErrorException e) {
        ResponseCode code = ResponseCode.BAD_REQUEST;
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // 잘못된 주소로 요청 한 경우
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        ResponseCode code = ResponseCode.NOT_FOUND;
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // Null 값이 발생한 경우
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<Object>> handleNullPointerException(NullPointerException e) {
        ResponseCode code = ResponseCode.NULL_POINT;
        print(new Object(){}, code.getMessage(), e);

        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code));
    }

    // 로그 출력
    private void print(Object object, String message, Throwable e) {
        // 클래스명
        String className = object.getClass().getEnclosingClass().getName();
        log.error("[{}][{}] {}", className, e.getMessage(), message, e);
    }

    private void print(Object object, String message, BindingResult bindingResult) {
        // 클래스명
        String className = object.getClass().getEnclosingClass().getName();
        log.error("[{}}] {}", className, message, bindingResult);
    }
}
