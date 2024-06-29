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
}