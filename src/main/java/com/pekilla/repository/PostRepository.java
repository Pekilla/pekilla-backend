package com.pekilla.repository;

import com.pekilla.enums.Category;
import com.pekilla.model.Post;
import com.pekilla.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends IRepository<Post>, JpaRepository<Post, Long> {
    List<Post> findAllByIsActiveTrueOrderByAddedDateDesc();

    @Query(value = """
    SELECT * FROM post p WHERE p.is_active = 1
    AND (?1 = '' OR ?1 = p.category) AND (?2 = '' OR UPPER(p.title) LIKE %?2% OR UPPER(p.description) LIKE %?2%)
    """, nativeQuery = true)
    List<Post> searchPosts(String category, String content);
}
