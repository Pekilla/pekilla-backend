package com.pekilla.service.interfaces;

import com.pekilla.dto.CommentInfoDTO;

import java.util.List;

public interface ICommentService extends IService<CommentInfoDTO>{

    CommentInfoDTO getById(Long id);

    List<CommentInfoDTO> getAllCommentInPost(long postId);
}
