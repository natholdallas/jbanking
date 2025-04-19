package com.github.natholdallas.annotation.impl;

import com.github.natholdallas.annotation.RequireNumber;
import com.github.natholdallas.annotation.RequireNumber.NumberType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class RequireNumberImpl implements ConstraintValidator<RequireNumber, String> {

    private NumberType numberType;

    @Override
    public void initialize(RequireNumber constraintAnnotation) {
        this.numberType = constraintAnnotation.value();
    }

    @SneakyThrows(NumberFormatException.class)
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (numberType.equals(NumberType.INT)) {
            new BigInteger(value);
        }
        new BigDecimal(value);
        return true;
    }

}
