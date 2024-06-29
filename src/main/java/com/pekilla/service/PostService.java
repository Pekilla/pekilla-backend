package com.pekilla.service;

import com.pekilla.dto.PostDTO;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.TagRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
public class PostService implements IService<PostDTO> {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostDTO::fromPost).toList();
    }

    public PostService(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

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

    public Tag getTagFromContent(String content) {
        return tagRepository
            .findOneByContent(content)
            .orElseGet(() -> tagRepository.save(new Tag(content)));
    }

    public boolean createOrUpdate(@Valid @NotNull PostDTO postDto, Long userId) {
        // Get post to update / or create new Post
        Post post = (postDto.id() == null ? new Post() : postRepository.findOneById(postDto.id()).orElseThrow(PostNotFoundException::new));

        post.setTitle(postDto.title());
        post.setDescription(postDto.description());

        post.setTags(
            postDto.tags()
                .stream()
                .map(this::getTagFromContent)
                .toList()
        );

        // if new Post
        if(postDto.id() == null) {
            post.setCategory(postDto.category());
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
