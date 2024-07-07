package com.pekilla.service;

import com.pekilla.dto.CommentViewDTO;
import com.pekilla.dto.CreateUpdateCommentDTO;
import com.pekilla.exception.type.CommentNotFoundException;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.model.Comment;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class CommentService implements IService<CreateUpdateCommentDTO> {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public Comment getById(Long id) {
        return commentRepository.findOneById(id)
                .orElseThrow(CommentNotFoundException::new);
    }

    public List<Comment> getAllCommentInPost(long postId) {
        return commentRepository.findAllByPostId(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    public List<CommentViewDTO> getViewCommentsFromPost(long postId) {
        // need to be mutable to be reversed with Collections.reverse so wrapped it on ArrayList
        List<CommentViewDTO> list = new ArrayList<>(getAllCommentInPost(postId).stream().map(
                comment -> CommentViewDTO
                        .builder()
                            .id(comment.getId())
                            .message(comment.getMessage())
                            .username(comment.getAuthor().getUsername())
                            .addedDate(comment.getAddedDate())
                        .build()).toList());

        Collections.reverse(list);
        return list;
    }

    @Override
    public String create(CreateUpdateCommentDTO comment) {
        Post post = postService.getPostById(comment.postId());
        User user = userService.getUserById(comment.userId());
        commentRepository.save(Comment
                .builder()
                    .message(comment.message())
                    .author(user)
                    .post(post)
                .build());
        return "Comment has been published on the post";
    }

    @Override
    public boolean delete(long id) {
        commentRepository.delete(this.getById(id));
        return true;
    }

    @Override
    public String update(long id, CreateUpdateCommentDTO ent) {
        return "";
    }
}
