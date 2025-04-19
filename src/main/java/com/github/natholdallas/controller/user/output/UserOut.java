package com.github.natholdallas.controller.user.output;

import com.github.natholdallas.common.Permission;
import com.github.natholdallas.model.User;

public record UserOut(
    long id,
    String username,
    Permission permission,
    boolean isIdentify
) {
    public static UserOut of(User user) {
        return new UserOut(
            user.id,
            user.username,
            user.permission,
            user.idName != null
        );
    }
}
