package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import com.pekilla.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("${ALLOWED_URL}")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        return new ResponseEntity<>(postService.createOrUpdate(postDTO, userId), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updatePost(PostDTO postDTO, @RequestParam Long userId) {
        return this.createPost(postDTO, userId);
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
