package com.pekilla.repository;

import com.pekilla.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends IRepository<Comment>, JpaRepository<Comment,Long>  {
    Optional<List<Comment>> findAllByPostId(long postId);

    @Query(value = "DELETE c FROM Comment c where c.post_id =?1 ", nativeQuery = true)
    void deleteCommentByPostId(long postId);
}
