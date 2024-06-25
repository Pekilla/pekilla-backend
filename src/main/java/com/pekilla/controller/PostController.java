package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import com.pekilla.model.Post;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Version;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pekilla.service.PostService;


@CrossOrigin("*")
@RestController
@RequestMapping("/post")
public class PostController {
    
    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) throws RuntimeException {
        boolean b = postService.createOrUpdate(postDTO, userId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
        try {
            return ResponseEntity.ok(postService.delete(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("This post cannot be deleted | " + e.getMessage());
        }
    }
}
