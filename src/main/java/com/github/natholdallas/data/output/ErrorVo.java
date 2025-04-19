package com.github.natholdallas.data.output;

import com.github.natholdallas.common.InvalidException;

import java.util.List;

public record ErrorVo(String name, String message) {

    public static List<ErrorVo> buildError(ErrorVo... errors) {
        return List.of(errors);
    }

    public static void throwError(ErrorVo... errors) {
        throw new InvalidException(List.of(errors));
    }

}
