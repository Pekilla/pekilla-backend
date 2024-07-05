package com.pekilla.service;

import com.pekilla.dto.PostDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.enums.Category;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import com.pekilla.repository.CommentRepository;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.TagRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class PostService implements IService<PostDTO> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    public Post getPostById(long postId) {
        return postRepository.findOneById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    public PostViewDTO getPostDTOById(long postId) {
        return PostViewDTO.fromPost(getPostById(postId));
    }

    public List<PostViewDTO> getAllPosts() {
        return postRepository.findAllByIsActiveTrueOrderByAddedDateDesc()
                .stream()
                .map(PostViewDTO::fromPost)
                .toList();
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
    public boolean delete(long id) {
        Post post = this.getPostById(id);

        commentRepository.deleteCommentByPostId(post.getId());
        postRepository.deleteById(post.getId());
        return true;
    }

    public Tag getTagFromContent(String content) {
        return tagRepository
            .findOneByContent(content)
                .orElseGet(() -> tagRepository.save(new Tag(content)));
    }

    public PostViewDTO createOrUpdate(@Valid @NotNull PostDTO postDto) {
        // Get post to update / or create new Post
        Post post = (postDto.getId() == null ? new Post() : postRepository.findOneById(postDto.getId()).orElseThrow(PostNotFoundException::new));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        post.setTags(
            postDto.getTags()
                .stream()
                .map(this::getTagFromContent)
                .collect(Collectors.toSet())
        );

        // if new Post
        if (postDto.getId() == null) {
            post.setCategory(postDto.getCategory());
            post.setOriginalPoster(
                userRepository.findOneById(postDto.getUserId()).orElseThrow(UserNotFoundException::new)
            );
        }

        return PostViewDTO.fromPost(postRepository.save(post));
    }



    /**
     * Function to research post by content, category and/or tags.
     *
     * @param content User input that will be searched in title and description of each post.
     * @param category The category of the posts.
     * @param tags The tags of the posts.
     */
    public List<PostViewDTO> searchPosts(String content, String category, Set<String> tags) {
        try {
            // To verify that the category exist, if it doesn't, it throws an Exception.
            if(!category.isEmpty()) Category.valueOf(category);

            List<Post> posts = postRepository.searchPosts(category, content);

            // To verify the tags
            if(!tags.isEmpty()) {
                return posts
                    .stream()
                    .filter(post -> post.getTagContents().containsAll(tags))
                    .map(PostViewDTO::fromPost)
                    .toList();
            }

            else return posts
                .stream()
                .map(PostViewDTO::fromPost)
                .toList();
        } catch (IllegalArgumentException e) {
            System.out.println("Category does not exist");
            return List.of();
        }
    }
}
