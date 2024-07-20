package com.pekilla.service;

import com.pekilla.TestContainerConfig;
import com.pekilla.post.PostRepository;
import com.pekilla.post.PostService;
import com.pekilla.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest extends TestContainerConfig {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

//    @Test
//    void createOrUpdate_CreationOfAPost_ShouldThrowsConstraintViolationException() {
//        assertThrows(ConstraintViolationException.class, () -> postService.createOrUpdate(null, null));
//    }
}