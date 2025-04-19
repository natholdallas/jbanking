package com.github.natholdallas.controller.admin.output;

import com.github.natholdallas.common.Permission;
import com.github.natholdallas.model.User;

public record UserOut(
    long id,
    String username,
    String idName,
    String idCard,
    Permission permission
) {
    public static UserOut of(User user) {
        return new UserOut(
            user.id,
            user.username,
            user.idName,
            user.idCard,
            user.permission
        );
    }
}
