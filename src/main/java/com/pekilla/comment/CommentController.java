package com.pekilla.comment;

import com.pekilla.comment.dto.CreateUpdateCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${ALLOWED_URL}")
@RestController
@RequestMapping(path = "/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
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
