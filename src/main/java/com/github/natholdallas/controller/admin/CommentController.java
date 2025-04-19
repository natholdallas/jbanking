package com.github.natholdallas.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.admin.input.CommentUpdateIn;
import com.github.natholdallas.controller.admin.output.CommentOut;
import com.github.natholdallas.repository.CommentRepo;
import com.github.natholdallas.repository.ReplyRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("admin-comment")
@RequestMapping("/admin/comment")
public final class CommentController {

    private final CommentRepo commentRepo;
    private final ReplyRepo replyRepo;
    private final UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<CommentOut>> list() {
        var comment = commentRepo.findAll()
            .stream()
            .map(CommentOut::of)
            .toList();
        return new ResponseEntity<>(comment, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentOut> retrieve(@PathVariable Long id) {
        var comment = CommentOut.of(commentRepo.findById(id).get());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid CommentUpdateIn data) {
        var comment = commentRepo.findById(id).get();
        comment.title = data.title;
        comment.content = data.content;
        comment.closed = data.closed;
        commentRepo.save(comment);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        commentRepo.deleteAllById(ids);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        commentRepo.deleteById(id);
        return new ResponseEntity<>(OK);
    }

}
