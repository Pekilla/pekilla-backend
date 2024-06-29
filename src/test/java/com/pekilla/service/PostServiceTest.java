package com.pekilla.service;

import com.pekilla.TestContainerConfig;
import com.pekilla.dto.PostDTO;
import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest extends TestContainerConfig {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Test
    void createOrUpdate_CreationOfAPost_ShouldThrowsConstraintViolationException() {
        assertThrows(ConstraintViolationException.class, () -> postService.createOrUpdate(null, null));
    }

    @Test
    void createOrUpdate_CreationOfAPost_ShouldThrowsPostUniqueTitleException() {
        // (Setup) Save a user
        User originalPoster = userRepository.save(User.builder().password("asdfasdfasdfsadf").username("Jack GPT").build());

        // (Setup) Save a post and get is title.
        Post conflictPost = postRepository.save(
            new Post("Crash the test", "fasdfasdfasdfasdfasdfasdfsadf", Category.PROGRAMMING, originalPoster, null)
        );

        // (Test)
        assertThrows(
            PostUniqueTitleException.class,
            () -> postService.createOrUpdate(
                PostDTO.builder()
                    .title(conflictPost.getTitle())
                    .content("Testing the title")
                    .category(conflictPost.getCategory())
                    .tags(List.of("c++", "c", "java"))
                    .build(),
                originalPoster.getId()
            )
        );
    }
}