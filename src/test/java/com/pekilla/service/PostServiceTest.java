package com.pekilla.service;

import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import com.pekilla.service.interfaces.IPostService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IPostServiceTest {
    @Autowired
    PostRepository postRepository;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySql = new MySQLContainer<>("mysql:latest");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IPostService postService;

    @BeforeAll
    static void setUp() {
        mySql.start();
    }

    @AfterAll
    static void afterAll() {
        mySql.stop();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOrUpdate_with_null_dto() {
        //assertThrows(ConstraintViolationException.class, () -> postService.createOrUpdate(null, null));
    }

    @Test
    void createOrUpdate_with_title_exists() {
        User orignalPoster = userRepository.save(User.builder().password("asdfasdfasdfsadf").username("Jack GPT").build());

        String conflictTitle = postRepository.save(
            Post.builder()
                .title("asdf")
                .content("fasdfasdfasdfasdfasdfasdfsadf")
                .category(Category.ANIME)
                .originalPoster(orignalPoster)
                .build()
        ).getTitle();

        /*postService.createOrUpdate(
            PostDTO.builder()
                .title(conflictTitle)
                .content("Testing the title")
                .category(Category.ANIME)
                .build(), orignalPoster.getId());*/
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}