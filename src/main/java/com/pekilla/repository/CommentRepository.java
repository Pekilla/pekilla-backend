package com.pekilla.repository;

import com.pekilla.dto.CommentInfoDTO;
import com.pekilla.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentInfoDTO,Long> {

    public List<CommentInfoDTO> findAllByPostId(long postId);
}
