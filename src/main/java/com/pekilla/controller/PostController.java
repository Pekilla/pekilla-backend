package com.pekilla.controller;

import com.pekilla.dto.PostDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import com.pekilla.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


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
    public ResponseEntity<Post> getPostById(@PathVariable long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
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

    @Deprecated(forRemoval = true)
    @GetMapping("/all/containing/{input}")
    public ResponseEntity<List<PostViewDTO>> getAllPostsThatContain(@PathVariable String input) {
        return new ResponseEntity<>(postService.getAllPostsThatContain(input), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam(required = false) String content,
                                  @RequestParam(required = false) Category category,
                                  @RequestParam(required = false) Set<String> tags) {
        return postService.searchPosts(content, category, tags);
    }
}
