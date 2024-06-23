package com.pekilla.controller;

import com.pekilla.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PostService.class)
class PostControllerTest {

    /*
     * Referring to the convention,
     * every test should be named like that
     *
     * MethodName_StateUnderTest_ExpectedBehavior
     */

    @MockBean private PostService postService;

    @Test
    void getPostById() {
    }

    @Test
    void createPost() {
    }

    @Test
    void deletePost_DeletionOfAPost_ShouldGet200() {

    }
}