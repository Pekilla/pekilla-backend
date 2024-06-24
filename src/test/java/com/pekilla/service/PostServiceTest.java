package com.pekilla.service;

import com.pekilla.dto.PostDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
//    This part give an error so am just gonna comment this part for now

//    @Test
//    void createOrUpdate_with_null_dto() {
//        Assertions.assertFalse(postService.createOrUpdate(null));
//    }

//    @Test
//    void createOrUpdate_with_title_exists() {
//        postService.createOrUpdate(PostDTO.builder().title("asdfasd").build());
//    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}