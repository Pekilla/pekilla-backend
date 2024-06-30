package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.service.PostService;
import lombok.RequiredArgsConstructor;
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
    public List<PostViewDTO> getAllPostViews() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<PostViewDTO> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        return new ResponseEntity<>(postService.createOrUpdate(postDTO, userId), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<PostViewDTO> updatePost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        return new ResponseEntity<>(postService.createOrUpdate(postDTO, userId), HttpStatus.OK);
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
