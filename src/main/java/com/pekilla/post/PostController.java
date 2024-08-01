package com.pekilla.post;

import com.pekilla.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<?> search(@RequestParam(required = false, defaultValue = "") String content,
                          @RequestParam(required = false, defaultValue = "") String category,
                          @RequestParam(required = false, defaultValue = "") String[] tags,
                          @RequestParam(required = false, defaultValue = "0") int page
    ) {
        // -1 because for the user the page start at 1.
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, 10);

        if (content.isBlank() && category.isBlank() && tags.length == 0) {
            return postService.getAllPosts(pageable);
        }

        return postService.searchPosts(content, category, tags, pageable);
    }

    @GetMapping("/{postId}/full-view")
    public ResponseEntity<?> getPostFullView(@PathVariable long postId) {
        return postService.getPostFullView(postId);
    }
}
