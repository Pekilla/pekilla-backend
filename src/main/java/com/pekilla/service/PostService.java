package com.pekilla.service;

import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.exception.type.PostUniqueTitleException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Post;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pekilla.dto.PostDTO;

@Service
public class PostService implements IService<PostDTO> {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(PostDTO ent) {
        return "";
    }

    @Override
    public String update(long id, PostDTO ent) {
        return "";
    }

    @Override
    public String delete(long id) {
        postRepository.deleteById(id);
        return "Post deleted successfully";
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

    public Post getPostById(Long id) {
        return null;
    }

    public Post getPostByTitle(String title) {
        return null;
    }
}
