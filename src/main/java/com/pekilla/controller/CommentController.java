package com.pekilla.controller;

import com.pekilla.dto.CommentInfoDTO;
import com.pekilla.model.Comment;
import com.pekilla.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable long commentId) {
        try {
            return ResponseEntity.ok(commentService.getById(commentId));
        } catch (Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/post/{postId}/all")
    public ResponseEntity<List<Comment>> getAllCommentsInPost(@PathVariable long postId) {
        try {
            return ResponseEntity.ok(commentService.getAllCommentInPost(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/post/add")
    public ResponseEntity<String> addComment(CommentInfoDTO dto) {
            return ResponseEntity.ok(commentService.create(dto));
    }
}
