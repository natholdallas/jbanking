package com.github.natholdallas.controller.admin.output;

import com.github.natholdallas.model.Reply;

public record ReplyOut(
    long id,
    UserOut user,
    String content
) {
    public static ReplyOut of(Reply reply) {
        return new ReplyOut(
            reply.id,
            UserOut.of(reply.user),
            reply.content
        );
    }
}
