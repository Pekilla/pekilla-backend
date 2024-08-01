package com.pekilla.post;

import com.pekilla.category.Category;
import com.pekilla.category.CategoryRepository;
import com.pekilla.category.exception.CategoryNotFoundException;
import com.pekilla.comment.CommentRepository;
import com.pekilla.comment.CommentService;
import com.pekilla.global.interfaces.IService;
import com.pekilla.post.dto.PostDTO;
import com.pekilla.post.dto.PostFullViewDTO;
import com.pekilla.post.dto.PostViewDTO;
import com.pekilla.post.dto.PostViewSqlNativeDto;
import com.pekilla.post.exception.PostNotFoundException;
import com.pekilla.tag.Tag;
import com.pekilla.tag.TagRepository;
import com.pekilla.user.Customer;
import com.pekilla.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private final CommentService commentService;

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

    public Page<PostViewDTO> getAllPosts(Pageable pageable) {
        return postRepository.findAllByIsActiveTrueOrderByAddedDateDesc(pageable);
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
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = (isCreate ? new Post() : postRepository.findOneById(postDto.getId()).orElseThrow(PostNotFoundException::new));

        if(!isCreate && customer.getId() != post.getOriginalPoster().getId()) {
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
            post.setOriginalPoster(customer);
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
    public Page<?> searchPosts(String content, String category, String[] tags, Pageable pageable) {
        try {
            category = category.trim();
            content = content.trim();

            if (content.isEmpty() && category.isEmpty() && tags.length == 0) {
                return this.getAllPosts(pageable);
            }

            /*
                There is a bug if the user give only one char that is a letter it crashes.
                This happens only if the user give content alone.
                But if the user give content(just a letter) with either tag or category it won't crash.
                But if we just make a query like the postRepository.isLike() that to the same thing
                related to content it do not crash if we give a letter.

                Other bug is that if we give an invalid page number in the pageable
                the code crash, but it does not do it with the non-native query.
            */
            return postRepository.searchPosts(category.isEmpty() ? "" : getCategoryByName(category).getName(), content.isEmpty() ? "" : content.toLowerCase(), tags, pageable);
        } catch (CategoryNotFoundException e) {
            System.out.println("Category does not exist.");
            return Page.empty();
        }
        catch (Exception e) {
            System.out.println("WARNING IT CRASHED");
            return Page.empty();
        }
        finally {
            System.out.println("CONTENT "+content+" and length : "+content.length());
            System.out.println("CATEGORY "+category+" and length : "+category.length());
            System.out.println("TAGS "+ Arrays.toString(tags)+" and length : "+tags.length);
            //System.out.println("The likeable : "+postRepository.isLike(content));
        }
    }

    public ResponseEntity<?> getPostFullView(long postId) {
        try {
            return ResponseEntity.ok(
                new PostFullViewDTO(
                    this.getPostDTOById(postId),
                    commentService.getViewCommentsFromPost(postId)
                )
            );
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
    }
}
