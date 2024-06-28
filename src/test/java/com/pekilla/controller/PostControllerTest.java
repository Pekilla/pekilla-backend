package com.pekilla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pekilla.dto.PostDTO;
import com.pekilla.enums.Category;
import com.pekilla.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest{
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
        postService.create(PostDTO.builder().build());
    }

    @Test
    void createPost_CreationOfAPostShouldBeOK() throws Exception {
        var postDto = new PostDTO(10L, "How to make the compiler...", "When the compiler...", List.of("c", "cpp", "java"), Category.PROGRAMMING);

        this.mockMvc.perform(
            post("/post/create")
                .param("userId", "1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(postDto))
            )
            .andExpect(result -> {
                System.out.println(result.getResponse().getStatus());
                System.out.println(result.getResponse().getContentAsString());
            });
    }

    @Test
    void deletePost_DeletionOfAPost_ShouldBeOK() throws Exception {
        when(postService.delete(1)).thenReturn("Post deleted successfully");
        this.mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createPost_CreationOfAPost_ShouldBeBadRequest() throws Exception {
    }
}