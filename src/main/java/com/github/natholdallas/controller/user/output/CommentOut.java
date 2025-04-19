package com.github.natholdallas.controller.user.output;

import com.github.natholdallas.model.Comment;

import java.util.List;

public record CommentOut(
    long id,
    String title,
    String content,
    boolean closed,
    List<ReplyOut> replies
) {
    public static CommentOut of(Comment comment) {
        return new CommentOut(
            comment.id,
            comment.title,
            comment.content,
            comment.closed,
            comment.replies.stream().map(ReplyOut::of).toList()
        );
    }
}
