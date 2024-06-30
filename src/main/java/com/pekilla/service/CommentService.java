package com.pekilla.service;

import com.pekilla.dto.CommentInfoDTO;
import com.pekilla.dto.PostViewDTO;
import com.pekilla.exception.type.CommentNotFoundException;
import com.pekilla.exception.type.PostNotFoundException;
import com.pekilla.model.Comment;
import com.pekilla.model.Post;
import com.pekilla.model.User;
import com.pekilla.repository.CommentRepository;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class CommentService implements IService<CommentInfoDTO> {

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

    @Override
    public String create(CommentInfoDTO comment) {
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
    public String delete(long id) {
        return "";
    }

    @Override
    public String update(long id, CommentInfoDTO ent) {
        return "";
    }
}
