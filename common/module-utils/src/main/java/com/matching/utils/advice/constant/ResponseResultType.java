package com.matching.utils.advice.constant;

import lombok.Getter;

@Getter
public enum ResponseResultType {
    SUCCESS("SUCCESS"), FAILURE("FAILURE"), ERROR("ERROR");

    private final String message;

    ResponseResultType(String message) {
        this.message = message;
    }
}
