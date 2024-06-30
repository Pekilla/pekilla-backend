package com.pekilla.service;

import com.pekilla.dto.PostDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.TagRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PostService implements IService<PostDTO> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public List<PostViewDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostViewDTO::fromPost).toList();
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
        Post post = (postDto.getId() == null ? new Post() : postRepository.findOneById(postDto.getId()).orElseThrow(PostNotFoundException::new));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        post.setTags(
            postDto.getTags()
                .stream()
                .map(this::getTagFromContent)
                .toList()
        );

        // if new Post
        if(postDto.getId() == null) {
            post.setCategory(postDto.getCategory());
            post.setOriginalPoster(
                userRepository.findOneById(userId).orElseThrow(UserNotFoundException::new)
            );
        }

        postRepository.save(post);
        return true;
    }

    public Post getPostById(Long id) {
        return postRepository.findOneById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public Post getPostByTitle(String title) {
        return null;
    }
}
