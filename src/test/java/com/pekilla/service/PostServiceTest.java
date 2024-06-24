package com.pekilla.service;

import com.pekilla.dto.PostDTO;
import com.pekilla.enums.Category;
import com.pekilla.exception.type.PostUniqueTitleException;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.validation.annotation.Validated;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostServiceTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySql = new MySQLContainer<>("mysql:latest");

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostService postService;

    @Autowired
    public PostServiceTest(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postService = new PostService(postRepository, userRepository);
    }

    @BeforeAll
    static void setUpDatabase() {
        mySql.start();
    }

    @AfterAll
    static void afterAll() {
        mySql.stop();
    }

    @Test
    @Validated
    void createOrUpdate_with_null_dto() {
        assertThrows(ConstraintViolationException.class, () -> postService.createOrUpdate(null, null));
    }

    @Test
    void createOrUpdate_with_title_exists() {
        // (Setup) Save a user
        User originalPoster = userRepository.save(User.builder().password("asdfasdfasdfsadf").username("Jack GPT").build());

        // (Setup) Save a post and get is title.
        Post conflictPost = postRepository.save(
            new Post("Crash the test", "fasdfasdfasdfasdfasdfasdfsadf", Category.PROGRAMMING, originalPoster)
        );

        // (Test)
        assertThrows(
            PostUniqueTitleException.class,
            () -> postService.createOrUpdate(
                PostDTO.builder()
                    .title(conflictPost.getTitle())
                    .content("Testing the title")
                    .category(conflictPost.getCategory())
                    .build(),
                originalPoster.getId()
            )
        );
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}