package com.pekilla.post;

import com.pekilla.category.Category;
import com.pekilla.category.CategoryRepository;
import com.pekilla.category.exception.CategoryNotFoundException;
import com.pekilla.comment.CommentRepository;
import com.pekilla.global.interfaces.IService;
import com.pekilla.post.dto.PostDTO;
import com.pekilla.post.dto.PostViewDTO;
import com.pekilla.post.exception.PostNotFoundException;
import com.pekilla.tag.Tag;
import com.pekilla.tag.TagRepository;
import com.pekilla.user.User;
import com.pekilla.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class PostService implements IService<PostDTO> {
    private final PostRepository postRepository;
    private final UserService userService;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;

    public Category getCategoryByName(String category) {
        return categoryRepository.findOneByName(category).orElseThrow(CategoryNotFoundException::new);
    }

    public Post getPostById(long postId) {
        return postRepository.findOneById(postId)
            .orElseThrow(PostNotFoundException::new);
    }

    public PostViewDTO getPostDTOById(long postId) {
        return PostViewDTO.fromPost(getPostById(postId));
    }

    public List<PostViewDTO> getAllPosts() {
        return postRepository.findAllByIsActiveTrueOrderByLastModifiedDateDesc()
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

    public ResponseEntity<?> createOrUpdate(@Valid @NotNull PostDTO postDto, boolean isCreate) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = (isCreate ? new Post() : postRepository.findOneById(postDto.getId()).orElseThrow(PostNotFoundException::new));

        if(!isCreate && user.getId() != post.getOriginalPoster().getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot edit this post because it is not yours.");
        }

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        post.setTags(
            postDto.getTags()
                .stream()
                .map(this::getTagFromContent)
                .collect(Collectors.toSet())
        );

        if (isCreate) {
            post.setCategory(getCategoryByName(postDto.getCategory()));
            post.setOriginalPoster(user);
            post.setDatesForCreate();
        } else {
            post.setLastModifiedDate(LocalDateTime.now());
        }

        return ResponseEntity.ok(PostViewDTO.fromPost(postRepository.save(post)));
    }

    /**
     * Function to research post by content, category and/or tags.
     *
     * @param content  User input that will be searched in title and description of each post.
     * @param category The category of the posts.
     * @param tags     The tags of the posts.
     */
    public List<PostViewDTO> searchPosts(String content, String category, Set<String> tags) {
        try {
            // To see if the category exist, else throw exception
            if (!category.isEmpty()) getCategoryByName(category);

            List<Post> posts = postRepository.searchPosts(category, content);

            // To verify the tags
            if (!tags.isEmpty()) {
                return posts
                    .stream()
                    .filter(post -> post.getTagContents().containsAll(tags))
                    .map(PostViewDTO::fromPost)
                    .toList();
            } else return posts
                .stream()
                .map(PostViewDTO::fromPost)
                .toList();
        } catch (CategoryNotFoundException e) {
            System.out.println("Category does not exist.");
            return List.of();
        }
    }
}
