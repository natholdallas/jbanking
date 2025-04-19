package com.github.natholdallas.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller("global-error")
@RequestMapping("${server.error.path:${error.path:/error}}")
public final class ErrorController extends BasicErrorController {

    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return switch (getStatus(request)) {
            case NOT_FOUND -> new ModelAndView("/");
            default -> new ModelAndView();
        };
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        return new ResponseEntity<>(getStatus(request));
    }

}
