package com.pekilla.service;

import com.pekilla.TestContainerConfig;
import com.pekilla.post.PostRepository;
import com.pekilla.post.PostService;
import com.pekilla.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

class PostServiceTest extends TestContainerConfig {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PostService postService;

//    @Test
//    void createOrUpdate_CreationOfAPost_ShouldThrowsConstraintViolationException() {
//        assertThrows(ConstraintViolationException.class, () -> postService.createOrUpdate(null, null));
//    }
}