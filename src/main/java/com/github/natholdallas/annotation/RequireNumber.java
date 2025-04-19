package com.github.natholdallas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.natholdallas.annotation.impl.RequireNumberImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequireNumberImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireNumber {

    String message() default "不是一个数字";

    NumberType value() default NumberType.INT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    enum NumberType {
        INT,
        DECIMAL
    }

}
