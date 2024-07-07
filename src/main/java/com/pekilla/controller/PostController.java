package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@CrossOrigin("${ALLOWED_URL}")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostViewDTO> getPostById(@PathVariable long postId) {
        return ResponseEntity.ok(postService.getPostDTOById(postId));
    }

    @PostMapping("/create")
    public ResponseEntity<PostViewDTO> createPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createOrUpdate(postDTO), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<PostViewDTO> updatePost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createOrUpdate(postDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable long postId) {
        try {
            return ResponseEntity.ok(postService.delete(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/search")
    public List<PostViewDTO> search(@RequestParam(required = false, defaultValue = "") String content,
                                    @RequestParam(required = false, defaultValue = "") String category,
                                    @RequestParam(required = false, defaultValue = "") Set<String> tags) {
        if (content.isEmpty() && category.isEmpty() && tags.isEmpty()) {
            return postService.getAllPosts();
        }

        return postService.searchPosts(content, category, tags);
    }
}
