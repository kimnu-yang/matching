package com.matching.utils.validation.date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTimeValidator implements ConstraintValidator<DateTimeValid, String> {

    private String pattern;

    @Override
    public void initialize(DateTimeValid constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(this.pattern));
            return true;
        } catch (DateTimeParseException e) {
            log.error("[DateTimeValidator] ", e);
            return false;
        }
    }
}
