package com.github.natholdallas.common;

import com.github.natholdallas.data.output.ErrorVo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InvalidException extends RuntimeException {

    public final List<ErrorVo> errors;

}
