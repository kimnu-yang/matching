package com.matching.api.advice.response;

import com.matching.utils.advice.constant.ResponseResultType;
import com.matching.utils.advice.response.ResponseCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private String result;
    private Integer status;
    private String code;
    private String message;
    private T data;

    // 성공
    public static <T> ApiResponse<T> success(ResponseCode code, T data) {
        return new ApiResponse<>(ResponseResultType.SUCCESS.getMessage(), code.getStatus().value(), code.getCode(), data, code.getMessage());
    }

    public static ApiResponse<Void> success(ResponseCode code) {
        return new ApiResponse<>(ResponseResultType.SUCCESS.getMessage(), code.getStatus().value(), code.getCode(), null, code.getMessage());
    }

    // 에외 발생으로 에러
    public static ApiResponse<Object> error(ResponseCode code) {
        return new ApiResponse<>(ResponseResultType.ERROR.getMessage(), code.getStatus().value(), code.getCode(), null, code.getMessage());
    }

    // 호출 실패
    public static ApiResponse<Object> failure(ResponseCode code, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError fieldError) {
                errors.put(fieldError.getField(), error.getDefaultMessage());
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        }
        return new ApiResponse<>(ResponseResultType.FAILURE.getMessage(), code.getStatus().value(), code.getCode(), errors, code.getMessage());
    }

    public ApiResponse(String result, Integer status, String code, T data, String message) {
        this.result = result;
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
