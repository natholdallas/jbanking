package com.github.natholdallas.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.natholdallas.controller.user.input.CommentCreationIn;
import com.github.natholdallas.controller.user.output.CommentOut;
import com.github.natholdallas.model.Comment;
import com.github.natholdallas.repository.CommentRepo;
import com.github.natholdallas.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SaCheckLogin
@RequiredArgsConstructor
@RestController("user-comment")
@RequestMapping("/comment")
public final class CommentController {

    private final UserRepo userRepo;
    private final CommentRepo commentRepo;
    private final UserController user;

    @GetMapping
    public ResponseEntity<List<CommentOut>> list() {
        var comments = commentRepo.findAllByUser(userRepo.cur())
            .stream()
            .map(CommentOut::of)
            .toList();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentOut> retrieve(@PathVariable Long id) {
        var comment = CommentOut.of(commentRepo.findByIdAndUser(id, userRepo.cur()).get());
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CommentCreationIn data) {
        var comment = new Comment(
            null,
            userRepo.cur(),
            data.title,
            data.content,
            false,
            null
        );
        commentRepo.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> destroyBatch(@RequestBody List<Long> ids) {
        commentRepo.deleteAllByIdAndUser(ids, userRepo.cur());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        commentRepo.deleteByIdAndUser(id, userRepo.cur());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
