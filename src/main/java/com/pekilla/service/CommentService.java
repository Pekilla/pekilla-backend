package com.pekilla.service;

import com.pekilla.dto.CommentInfoDTO;
import com.pekilla.dto.UpdateCommentDTO;
import com.pekilla.repository.CommentRepository;
import com.pekilla.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired private CommentRepository commentRepository;


    @Override
    public CommentInfoDTO getById(Long id) {
        return null;
    }

    @Override
    public List<CommentInfoDTO> getAllCommentInPost(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public String create(UpdateCommentDTO ent) {
        return "";
    }

    @Override
    public String delete(long id) {
        return "";
    }

    @Override
    public String update(long id, UpdateCommentDTO ent) {
        return "";
    }
}
