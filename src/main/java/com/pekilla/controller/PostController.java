package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pekilla.service.PostService;


@RestController
@RequestMapping(path = "/post")
public class PostController {
    
    @Autowired private PostService postService;

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        try {
            return ResponseEntity.ok(postService.create(postDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Post cannot be created | " + e.getMessage());
        }
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
