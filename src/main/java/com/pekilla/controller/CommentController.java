package com.pekilla.controller;

import com.pekilla.dto.CommentViewDTO;
import com.pekilla.dto.CreateUpdateCommentDTO;
import com.pekilla.model.Comment;
import com.pekilla.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("${ALLOWED_URL}")
@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postId}/all")
    public ResponseEntity<List<CommentViewDTO>> getAllCommentsInPost(@PathVariable long postId) {
        try {
            return ResponseEntity.ok(commentService.getViewCommentsFromPost(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/post/add")
    public ResponseEntity<String> addComment(CreateUpdateCommentDTO dto) {
        try {
            return ResponseEntity.ok(commentService.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cannot Add this comment | " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }

}
