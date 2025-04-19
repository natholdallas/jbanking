package com.github.natholdallas.common;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpUtil;
import com.github.natholdallas.data.output.ErrorVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Object> notLogin() {
        StpUtil.logout();
        return new ResponseEntity<>(UNAUTHORIZED);
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResponseEntity<Object> notPermission() {
        return new ResponseEntity<>(FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<List<ErrorVo>> noSuchElement() {
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorVo>> methodArgument(MethodArgumentNotValidException e) {
        List<ErrorVo> errors = e.getAllErrors()
            .stream()
            .map(it -> {
                if (it instanceof FieldError fieldError) {
                    return new ErrorVo(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return new ErrorVo(it.getObjectName(), it.getDefaultMessage());
            })
            .toList();
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<List<ErrorVo>> invalid(InvalidException e) {
        return new ResponseEntity<>(e.errors, BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<List<ErrorVo>> numberFormat() {
        return new ResponseEntity<>(
            List.of(
                new ErrorVo("NumberFormat", "不是一个数字")
            ),
            BAD_REQUEST
        );
    }

}
