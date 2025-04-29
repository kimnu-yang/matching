package com.matching.api.advice;

import com.matching.api.advice.response.ApiResponse;
import com.matching.utils.advice.exception.CustomException;
import com.matching.utils.advice.response.ResponseCode;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Hidden
public class ErrorRestController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ApiResponse<ResponseCode>> error(HttpServletRequest request) {
        throw new CustomException(ResponseCode.AUTHENTICATION_ENTRYPOINT);
    }
}
