package com.pekilla.controller;

import com.pekilla.model.Comment;
import com.pekilla.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

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
}
