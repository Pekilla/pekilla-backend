package com.pekilla.service;

import com.pekilla.dto.CommentInfoDTO;
import com.pekilla.model.Comment;
import com.pekilla.repository.CommentRepository;
import com.pekilla.repository.PostRepository;
import com.pekilla.repository.UserRepository;
import com.pekilla.service.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentInfoDTO getById(Long id) {
        return null;
    }

    @Override
    public List<CommentInfoDTO> getAllCommentInPost(long postId) {
        //return commentRepository.findAllByPostId(postId);
        return null;
    }

    @Override
    public String create(CommentInfoDTO comment) {
        return "";
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
