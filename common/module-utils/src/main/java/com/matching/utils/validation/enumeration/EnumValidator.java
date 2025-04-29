package com.matching.utils.validation.enumeration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValid, Enum<?>> {
    private EnumValid enumValid;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.enumValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        Object[] enumValues = this.enumValid.enumClass().getEnumConstants();
        if (enumValues == null || value == null) {
            return false;
        }

        return Arrays.asList(enumValues).contains(value);

    }
}
