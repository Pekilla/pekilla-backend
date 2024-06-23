package com.pekilla.controller;

import com.pekilla.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    /*
     * Referring to the convention,
     * every test should be named like that
     *
     * MethodName_StateUnderTest_ExpectedBehavior
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void getPostById() {
    }

    @Test
    void createPost() {
    }

    @Test
    void deletePost_DeletionOfAPost_ShouldBeOK() throws Exception {
        when(postService.delete(1)).thenReturn("Post deleted successfully");
        this.mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isOk());
    }
}