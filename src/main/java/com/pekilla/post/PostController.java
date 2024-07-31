package com.pekilla.post;

import com.pekilla.post.dto.PostDTO;
import com.pekilla.post.dto.PostViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        return postService.createOrUpdate(postDTO, true);
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
        return postService.createOrUpdate(postDTO, false);
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
                                    @RequestParam(required = false, defaultValue = "") String[] tags) {
        if (content.isEmpty() && category.isEmpty() && tags.length == 0) {
            return postService.getAllPosts();
        }

        return postService.searchPosts(content, category, tags);
    }

    @GetMapping("/{postId}/full-view")
    public ResponseEntity<?> getPostFullView(@PathVariable long postId) {
        return postService.getPostFullView(postId);
    }
}
