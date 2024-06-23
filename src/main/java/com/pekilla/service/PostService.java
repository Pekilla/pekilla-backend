package com.pekilla.service;

import com.pekilla.enums.Category;
import com.pekilla.exception.type.CategoryNotFoundException;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.exception.type.PostUniqueTitleException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import com.pekilla.service.interfaces.IPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pekilla.dto.PostDTO;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(PostDTO ent) {
        return "";
    }

    @Override
    public String delete(long id) {
        postRepository.deleteById(id);
        return "Post deleted successfully";
    }

    @Override
    public String update(long id, PostDTO ent) {
        return "";
    }

    public boolean createOrUpdate(@NotNull PostDTO ent, Long userId) throws RuntimeException {
        // Check if title exists
        postRepository
            .findOneByCategoryAndTitle(ent.category(), ent.title())
            .orElseThrow(() -> new PostUniqueTitleException(ent.title(), ent.category().toString())
        );

        // Get post to update / or create new Post
        Post post = (ent.id() == null ? new Post() : postRepository.findOneById(ent.id()).orElseThrow(PostNotFoundException::new));

        post.setTitle(ent.title());
        post.setContent(ent.content());

        // NEED TO HANDLE TAGS

        // if new Post
        if(ent.id() == null) {
            post.setCategory(ent.category());
            post.setOriginalPoster(
                userRepository.findOneById(userId).orElseThrow(UserNotFoundException::new)
            );
        }

        postRepository.save(post);
        return true;
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public Post getPostByTitle(String title) {
        return null;
    }
}
